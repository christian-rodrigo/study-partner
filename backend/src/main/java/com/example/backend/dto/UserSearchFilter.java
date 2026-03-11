package com.example.backend.dto;

import com.example.backend.entity.*;
import lombok.Data;

@Data
public class UserSearchFilter {

    private String university;
    private String city;
    private String degreeProgram;
    private Integer semester;

    private String language;
    private String availableTime;

    private StudyMode studyMode;

    private LearningStyle learningStyle;
    private LearningGoal learningGoal;
    private StudyFrequency studyFrequency;

    private Integer minAge;
    private Integer maxAge;

    private String keyword;

    private LanguageLevel languageLevel;

    private String course;
}