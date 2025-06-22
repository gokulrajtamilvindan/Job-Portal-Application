package com.naukri.central_api.connector;

import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.model.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class DataBaseApiConnector extends RestApi{

    @Value("${database.api.baseurl}")
    String baseUrl;

    ModelMapper modelMapper = new ModelMapper();

    public AppUser getUserByEmailEndpoint(String email) {

        String endpoint = baseUrl + "/user/email" + email;
        Object response = this.makeGetCall(endpoint, new HashMap<>());
        return modelMapper.map(response, AppUser.class);

    }

    public Skill callGetSkillByNameEndpoint(String skillName) {

        String url = baseUrl + "/skill/get/" + skillName;
        Object response = this.makeGetCall(url, new HashMap<>());
        return modelMapper.map(response, Skill.class);

    }

    public AppUser callSaveUserEndpoint(AppUser user) {

        String url = baseUrl + "/user/save";
        Object response = this.makePostCall(url, user, new HashMap<>());
        return modelMapper.map(response, AppUser.class);

    }

    public Skill callSaveSkillEndpoint(Skill skill) {

        String url = baseUrl + "/skill/save";
        Object response = this.makePostCall(url, skill, new HashMap<>());
        return modelMapper.map(response, Skill.class);

    }

    public Company callSaveCompanyEndpoint(Company company) {

        String url = baseUrl + "/company/save";
        Object response = this.makePostCall(url, company, new HashMap<>());
        return modelMapper.map(response, Company.class);

    }

}
