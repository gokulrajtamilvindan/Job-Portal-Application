package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Skill;
import com.naukri.central_api.utility.AuthUtility;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    SkillService skillService;
    MappingUtility mappingUtility;
    DataBaseApiConnector dbApiConnector;
    AuthUtility authUtility;

    @Autowired
    public UserService(SkillService skillService,
                       MappingUtility mappingUtility,
                       DataBaseApiConnector dbApiConnector,
                       AuthUtility authUtility) {
        this.skillService = skillService;
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.authUtility = authUtility;
    }

    public AppUser registerJobSeeker(JobSeekerRegistrationDto jobSeekerDto){

        List<String> skillNames = jobSeekerDto.getSkillSet();
        List<Skill> skills = skillService.getAllSkills(jobSeekerDto.getSkillSet());
        AppUser jobSeeker = mappingUtility.mapJobSeekerDetailsToAppUser(jobSeekerDto, skills);
        AppUser user = this.saveUser(jobSeeker);
        return user;

    }

    public boolean validateCredentials(String email, String password) {

        AppUser user = dbApiConnector.callGetUserByEmailEndpoint(email);
        if (user.getPassword().equals(password)) {
            return true;
        }

        return false;

    }

    public AppUser getUserFromToken(String token) {

        String email = authUtility.decryptJwtToken(token).split(":")[0];
        return dbApiConnector.callGetUserByEmailEndpoint(email);

    }

    public boolean isAdminUser(AppUser user) {
        return user.getUserType() == "Admin" ? true : false;
    }

    AppUser saveUser(AppUser user) {

        return dbApiConnector.callSaveUserEndpoint(user);

    }

}
