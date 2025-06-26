package com.naukri.central_api.controller;

import com.naukri.central_api.dto.CompanyRegistrationDto;
import com.naukri.central_api.dto.JwtTokenResponseDto;
import com.naukri.central_api.dto.RecruiterDetailsDto;
import com.naukri.central_api.exception.UnAuthorizedException;
import com.naukri.central_api.model.AppUser;
import com.naukri.central_api.model.Company;
import com.naukri.central_api.service.CompanyService;
import com.naukri.central_api.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/company")
public class CompanyController {

    CompanyService companyService;
    AuthUtility authUtility;

    @Autowired
    public CompanyController(CompanyService companyService,
                             AuthUtility authUtility) {
        this.companyService = companyService;
        this.authUtility = authUtility;
    }

    @PostMapping("/register")
    public ResponseEntity registerCompany(@RequestBody CompanyRegistrationDto companyRegistrationDto) {

        Company company = companyService.registerCompany(companyRegistrationDto);
        String token = authUtility.generateToken(companyRegistrationDto.getEmail(),
                companyRegistrationDto.getPassword(),
                "ADMIN");
        JwtTokenResponseDto tokenResponse = new JwtTokenResponseDto(token);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);

    }

    @PostMapping("/invite-recruiter")
    public ResponseEntity inviteRecruiter(@RequestBody RecruiterDetailsDto recruiterDetailsDto,
                                          @RequestHeader String Authorization) {

        AppUser recruiter = companyService.inviteRecruiter(recruiterDetailsDto, Authorization);
        return new ResponseEntity<>(recruiter, HttpStatus.CREATED);

    }

    @PutMapping("/accept-invitation/{token}")
    public ResponseEntity acceptInvitation(@PathVariable String token) {
        try {
            AppUser recruiter = companyService.acceptInvitation(token);
            return new ResponseEntity<>(recruiter, HttpStatus.CREATED);
        } catch (UnAuthorizedException e) {
            return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
        }
    }

}
