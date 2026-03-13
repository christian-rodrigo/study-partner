package com.example.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Table(name = "tutor_profiles")
public class TutorProfile {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private Integer hourlyRate;

    private String description;

    private String availability;

    private Boolean online;


}
