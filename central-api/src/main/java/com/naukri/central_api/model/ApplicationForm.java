package com.naukri.central_api.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ApplicationForm {
    UUID id;
    List<Questions> questionsList;
}
