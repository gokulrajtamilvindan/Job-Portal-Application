package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    MappingUtility mappingUtility;
    DataBaseApiConnector dbApiConnector;
    UserService userService;

    @Autowired
    public CompanyService(MappingUtility mappingUtility,
                          DataBaseApiConnector dbApiConnector,
                          UserService userService) {
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
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

}
