package com.naukri.database_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    boolean isMandatory;

    @ManyToMany
    List<Questions> questionsList;
}
