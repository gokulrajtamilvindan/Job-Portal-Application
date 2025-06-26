package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.connector.NotificationApiConnector;
import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.exception.UnAuthorizedException;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.utility.AuthUtility;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    MappingUtility mappingUtility;
    DataBaseApiConnector dbApiConnector;
    UserService userService;
    NotificationApiConnector notificationApiConnector;
    AuthUtility authUtility;

    @Autowired
    public CompanyService(MappingUtility mappingUtility,
                          DataBaseApiConnector dbApiConnector,
                          UserService userService,
                          NotificationApiConnector notificationApiConnector,
                          AuthUtility authUtility) {
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.notificationApiConnector = notificationApiConnector;
        this.authUtility = authUtility;
    }

    /**
     * Expectation of this function is to save company details in the company table
     * To Save company details it will be calling database Api connector which will be hitting request to
     * database api company controller save endpoint
     * @return Company
     */

    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto) {

        Company company = mappingUtility.mapCompanyDtoToCompanyModel(companyRegistrationDto);
        company = this.save(company);

        AppUser admin = mappingUtility.mapCompanyDtoToAdmin(companyRegistrationDto, company);

        userService.saveUser(admin);

        return company;

    }


    /**
     * This save method will internally call database api connector which will be calling database api save company endpoint.
     * @return Company
     */

    public Company save(Company company) {

        return dbApiConnector.callSaveCompanyEndpoint(company);

    }

    public AppUser inviteRecruiter(RecruiterDetailsDto recruiterDetailsDto,
                                   String Authorization) {

        String token = Authorization.substring(7);
        AppUser admin = userService.getUserFromToken(token);
        if (!userService.isAdminUser(admin)) {
            throw new UnAuthorizedException("Invalid Operation");
        }
        Company company = admin.getCompany();

        AppUser recruiter = mappingUtility.mapRecruiterDtoToAppUser(recruiterDetailsDto, company);
        recruiter = userService.saveUser(recruiter);

        token = authUtility.generateToken(recruiter.getEmail(), recruiter.getPassword(), "RECRUITER");

        notificationApiConnector.callInviteRecruiterEndpoint(recruiter, token);
        return recruiter;

    }

    public AppUser acceptInvitation(String token) {
        String [] payload = userService.decryptJwtToken(token).split(":");
        String email = payload[0];
        String password = payload[1];
        String role = payload[2];
        if (!userService.validateCredentials(email, password)) {
            throw new UnAuthorizedException("Invalid Credentials");
        }
        AppUser recruiter = userService.getUserFromToken(token);
        if(userService.isUserRecruiter(recruiter)) {
            throw new UnAuthorizedException("Invalid Operation");
        }
        recruiter.setStatus("ACTIVE");
        userService.saveUser(recruiter);
        
        String adminEmail = recruiter.getCompany().getEmail();
        AppUser admin = userService.getUserByEmail(adminEmail);
        List<AppUser> mailDetails = new ArrayList<>();
        mailDetails.add(recruiter);
        mailDetails.add(admin);

        notificationApiConnector.callAcceptInvitationEndpoint(mailDetails);
        return recruiter;
    }

}
