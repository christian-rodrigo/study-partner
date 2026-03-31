package com.example.backend.dto;

import com.example.backend.enums.LearningGoal;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyFrequency;
import com.example.backend.enums.StudyMode;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;

    private Long universityId;
    private String universityName;
    private String city;
    private String degreeProgram;
    private Integer semester;
    private String bio;

    private String language;
    private String availableTime;
    private StudyMode studyMode;

    private LearningStyle learningStyle;
    private LearningGoal learningGoal;
    private StudyFrequency studyFrequency;

}