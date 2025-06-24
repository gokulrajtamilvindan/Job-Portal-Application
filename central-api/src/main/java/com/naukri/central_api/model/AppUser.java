package com.naukri.central_api.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {

    UUID id;
    String name;
    String email;
    String password;
    String status;
    Long phoneNumber;
    String userType;
    Company company;
    List<Skill> skillSet;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
