package com.naukri.central_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateJobDto {
    String state;
    String shortDescription;
    String location;
    String jobDescription;
    List<String> skills;
    List<String> questions;
}
