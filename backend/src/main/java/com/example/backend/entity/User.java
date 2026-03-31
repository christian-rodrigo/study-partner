package com.example.backend.entity;

import com.example.backend.enums.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private Integer age;
    private String city;
    private String degreeProgram;
    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Column(length = 1000)
    private String bio;

    private String language;
    private String availableTime;

    @Enumerated(EnumType.STRING)
    private StudyMode studyMode;

    @Enumerated(EnumType.STRING)
    private LearningStyle learningStyle;

    @Enumerated(EnumType.STRING)
    private LearningGoal learningGoal;

    @Enumerated(EnumType.STRING)
    private StudyFrequency studyFrequency;
    @Enumerated(EnumType.STRING)
    private LanguageLevel languageLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType role;
}