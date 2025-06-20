package com.naukri.central_api.controller;

import com.naukri.central_api.dto.JobSeekerRegistrationDto;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/user")
public class AppUserController {

    UserService userService;

    @Autowired
    public AppUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerJobApplicant(@RequestBody JobSeekerRegistrationDto jobSeekerDto) {
        AppUser user = userService.registerJobSeeker(jobSeekerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
