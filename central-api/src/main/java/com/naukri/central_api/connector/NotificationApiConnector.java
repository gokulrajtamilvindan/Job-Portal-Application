package com.naukri.central_api.connector;

import com.naukri.central_api.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NotificationApiConnector extends RestApi{

    @Value("${notification.api.baseurl}")
    String baseUrl;

    public void callInviteRecruiterEndpoint (AppUser recruiter) {
        String url = baseUrl + "/company/invite-recruiter";
        this.makePutCall(url, recruiter, new HashMap<>());
    }

}
