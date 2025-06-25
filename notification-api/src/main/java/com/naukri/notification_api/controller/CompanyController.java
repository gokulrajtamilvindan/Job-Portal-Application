package com.naukri.notification_api.controller;

import com.naukri.notification_api.model.AppUser;
import com.naukri.notification_api.service.CompanyService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification/company")
public class CompanyController {

    CompanyService companyService;

    @Autowired
    public CompanyController (CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping("/invite-recruiter")
    public void sendInvitationMailToRecruiter(@RequestBody AppUser recruiter) throws MessagingException {
        companyService.sendInvitationMailToRecruiter(recruiter);
    }

}
