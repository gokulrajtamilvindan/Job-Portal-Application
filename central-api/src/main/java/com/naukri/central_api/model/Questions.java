package com.naukri.central_api.model;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Questions {

    UUID id;
    String question;
    boolean isMandatory;
}
