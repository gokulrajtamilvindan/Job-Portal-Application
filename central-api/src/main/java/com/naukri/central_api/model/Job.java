package com.naukri.central_api.model;

import lombok.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Job {

    UUID id;
    String state;

    String shortDescription;

    String location;

    String jobDescription;

    ApplicationForm applicationForm;

    AppUser createdBy; // This is the recruiter who created the job
    LocalDateTime postedDate;
    int totalApplicants;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    List<Skill> skills;

    List<FormSubmission> jobApplications;
}
