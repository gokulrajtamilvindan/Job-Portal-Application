package com.naukri.database_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String state;

    @Column(nullable = false)
    String shortDescription;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String jobDescription;

    @OneToOne
    ApplicationForm applicationForm;

    @ManyToOne
    AppUser createdBy; // This is the recruiter who created the job

    LocalDateTime postedDate;

    int totalApplicants;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ManyToMany
    List<Skill> skills;

    @OneToMany
    List<FormSubmissions> jobApplications;
}
