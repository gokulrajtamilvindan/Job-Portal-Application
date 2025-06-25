package com.naukri.notification_api.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Skill {

    UUID id;
    String name;

}