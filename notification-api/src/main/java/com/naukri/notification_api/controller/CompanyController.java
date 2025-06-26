package com.naukri.notification_api.controller;

import com.naukri.notification_api.model.AppUser;
import com.naukri.notification_api.service.CompanyService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification/company")
public class CompanyController {

    CompanyService companyService;

    @Autowired
    public CompanyController (CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping("/invite-recruiter/{token}")
    public void sendInvitationMailToRecruiter(@RequestBody AppUser recruiter, @PathVariable String token) throws MessagingException {
        companyService.sendInvitationMailToRecruiter(recruiter, token);
    }

    @PutMapping("/accept-invitation")
    public void sendAcceptInvitationMailToAdmin(@RequestBody List<AppUser> mailDetails) throws MessagingException {
        AppUser recruiter = mailDetails.get(0);
        AppUser admin = mailDetails.get(1);
        companyService.sendAcceptMailNotificationToAadmin(recruiter, admin);
    }

}
