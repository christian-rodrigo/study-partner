package com.example.backend.dto;

import com.example.backend.enums.LearningGoal;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyFrequency;
import com.example.backend.enums.StudyMode;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserProfileRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    private Long universityId;

    private String city;

    private String degreeProgram;

    private Integer semester;

    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    private String language;

    private String availableTime;

    private StudyMode studyMode;

    private LearningStyle learningStyle;

    private LearningGoal learningGoal;

    private StudyFrequency studyFrequency;

}