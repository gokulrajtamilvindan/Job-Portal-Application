package com.naukri.central_api.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {

    UUID id;
    String companyName;
    String email;
    String websiteLink;
    String linkedinLink;
    int companySize;
    String industry;

}
