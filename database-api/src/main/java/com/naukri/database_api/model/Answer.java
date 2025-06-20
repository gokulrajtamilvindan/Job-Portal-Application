package com.naukri.database_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String answer;

    @ManyToOne
    Questions question;
}
