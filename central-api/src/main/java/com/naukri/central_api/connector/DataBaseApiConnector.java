package com.naukri.central_api.connector;

import com.naukri.central_api.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class DataBaseApiConnector extends RestApi{

    @Value("${database.api.baseurl}")
    String baseUrl;

    ModelMapper modelMapper = new ModelMapper();

    public AppUser callGetUserByEmailEndpoint(String email) {

        String endpoint = baseUrl + "/user/email/" + email;
        Object response = this.makeGetCall(endpoint, new HashMap<>());
        if (response == null) {
            return null;
        }
        return modelMapper.map(response, AppUser.class);

    }

    public Skill callGetSkillByNameEndpoint(String skillName) {

        String url = baseUrl + "/skill/get/" + skillName;
        Object response = this.makeGetCall(url, new HashMap<>());
        if (response == null) {
            return null;
        }
        return modelMapper.map(response, Skill.class);

    }

    public AppUser callSaveUserEndpoint(AppUser user) {

        String url = baseUrl + "/user/save";
        Object response = this.makePostCall(url, user, new HashMap<>());
        if (response == null) {
            return null;
        }
        return modelMapper.map(response, AppUser.class);

    }

    public Skill callSaveSkillEndpoint(Skill skill) {

        String url = baseUrl + "/skill/save";
        Object response = this.makePostCall(url, skill, new HashMap<>());
        if (response == null) {
            return null;
        }
        return modelMapper.map(response, Skill.class);

    }

    public Company callSaveCompanyEndpoint(Company company) {

        String url = baseUrl + "/company/save";
        Object response = this.makePostCall(url, company, new HashMap<>());
        if (response == null) {
            return null;
        }
        return modelMapper.map(response, Company.class);

    }

    public Questions callCreateQuestionEndpoint(Questions questions) {
        String url = baseUrl + "/questions/save";
        Object response = this.makePostCall(url, questions, new HashMap<>());
        return modelMapper.map(response, Questions.class);
    }

    public Job callSaveJobEndpoint(Job job){
        String endpoint = baseUrl + "/job/save";
        Object response = this.makePostCall(endpoint, job, new HashMap<>());
        return modelMapper.map(response, Job.class);
    }

}
