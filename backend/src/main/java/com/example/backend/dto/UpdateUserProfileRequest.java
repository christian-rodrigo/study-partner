package com.example.backend.dto;

import com.example.backend.entity.LearningGoal;
import com.example.backend.entity.LearningStyle;
import com.example.backend.entity.StudyFrequency;
import com.example.backend.entity.StudyMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserProfileRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "University is required")
    private Long universityId;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Degree program is required")
    private String degreeProgram;

    @NotNull(message = "Semester is required")
    private Integer semester;

    private String bio;
    private String language;
    private String availableTime;
    private StudyMode studyMode;
    private LearningStyle learningStyle;
    private LearningGoal learningGoal;
    private StudyFrequency studyFrequency;
}