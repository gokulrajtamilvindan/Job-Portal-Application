package com.naukri.central_api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyRegistrationDto {

    private String companyName;
    private String email;
    private String password;
    private Long phoneNumber;
    private String websiteLink;
    private String linkedinLink;
    private int companySize;
    private String industry;

}
