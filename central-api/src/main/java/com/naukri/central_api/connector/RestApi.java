package com.naukri.central_api.connector;


import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestApi {

    public Object makePostCall(String url, Object body) {

        RequestEntity request = RequestEntity.post(url).body(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        return response.getBody();

    }

}
