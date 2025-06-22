package com.naukri.central_api.connector;


import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestApi {

    public Object makePostCall(String url, Object body, Map<String, String> queryParams) {

        url = this.addQueryParams(url, queryParams);
        RequestEntity request = RequestEntity.post(url).body(body);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        return response.getBody();

    }

    public String addQueryParams(String url, Map<String, String> map) {

        if(map.size() == 0) {
            return url;
        }

        url += "?";

        int idx = 1;

        for (String key : map.keySet()) {
            url += key + "=" + map.get(key);
            if (idx < map.size()) {
                url += "&";
            }
        }

        return url;

    }

    public Object makeGetCall(String url, Map<String, String> queryParams) {

        url = this.addQueryParams(url, queryParams);
        RequestEntity request = RequestEntity.get(url).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        return response.getBody();

    }

    public Object makePutCall(String url, Object body, Map<String, String> queryParams) {

        url = this.addQueryParams(url, queryParams);
        RequestEntity request = RequestEntity.put(url).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
        return response.getBody();

    }

    public Object makeDeleteCall(String url, Map<String, String> queryParams) {

        url = this.addQueryParams(url, queryParams);
        RequestEntity request = RequestEntity.delete(url).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, request, Object.class);
        return response.getBody();

    }

}
