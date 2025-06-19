package com.naukri.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(nullable = false)
    String companyName;

    @Column(unique = true, nullable = false)
    String email;

    @Column(unique = true, nullable = false)
    String websiteLink;

    @Column(unique = true, nullable = false)
    String linkedinLink;

    @Column(nullable = false)
    int companySize;

    @Column(nullable = false)
    String industry;
}
