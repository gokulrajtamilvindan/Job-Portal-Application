package com.naukri.central_api.connector;

import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.model.Skill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataBaseApiConnector {

    @Value("${database.api.baseurl}")
    String baseUrl;

    public Skill callGetSkillByNameEndpoint(String skillName) {

        String url = baseUrl + "/skill/get/" + skillName;

        RequestEntity request = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Skill> response = restTemplate.exchange(url, HttpMethod.GET, request, Skill.class);

        return response.getBody();

    }

    public AppUser callSaveUserEndpoint(AppUser user) {

        String url = baseUrl + "/user/save";

        RequestEntity request = RequestEntity.post(url).body(user);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);

        return response.getBody();

    }

    public Skill callSaveSkillEndpoint(Skill skill) {

        String url = baseUrl + "/skill/save";

        RequestEntity request = RequestEntity.post(url).body(skill);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Skill> response = restTemplate.exchange(url, HttpMethod.POST, request, Skill.class);

        return response.getBody();

    }

    public Company callSaveCompanyEndpoint(Company company) {

        String url = baseUrl + "/company/save";

        RequestEntity request = RequestEntity.post(url).body(company);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Company> response = restTemplate.exchange(url, HttpMethod.POST, request, Company.class);

        return response.getBody();

    }

}
