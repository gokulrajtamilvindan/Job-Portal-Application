package com.naukri.central_api.service;

import com.naukri.central_api.connector.DataBaseApiConnector;
import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Skill;
import com.naukri.central_api.utility.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    SkillService skillService;
    MappingUtility mappingUtility;
    DataBaseApiConnector dbApiConnector;

    @Autowired
    public UserService(SkillService skillService,
                       MappingUtility mappingUtility,
                       DataBaseApiConnector dbApiConnector) {
        this.skillService = skillService;
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
    }

    public AppUser registerJobSeeker(JobSeekerRegistrationDto jobSeekerDto){

        List<String> skillNames = jobSeekerDto.getSkillSet();
        List<Skill> skills = skillService.getAllSkills(jobSeekerDto.getSkillSet());
        AppUser jobSeeker = mappingUtility.mapJobSeekerDetailsToAppUser(jobSeekerDto, skills);
        AppUser user = this.saveUser(jobSeeker);
        return user;

    }

    AppUser saveUser(AppUser user) {

        return dbApiConnector.callSaveUserEndpoint(user);

    }

}
