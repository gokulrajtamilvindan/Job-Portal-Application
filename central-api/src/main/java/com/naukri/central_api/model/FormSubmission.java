package com.naukri.central_api.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormSubmission {
    UUID id;
    List<Answer> answers;
}
