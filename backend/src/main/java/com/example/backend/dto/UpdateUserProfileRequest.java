package com.example.backend.dto;

import com.example.backend.entity.LearningGoal;
import com.example.backend.entity.LearningStyle;
import com.example.backend.entity.StudyFrequency;
import com.example.backend.entity.StudyMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserProfileRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "University id is required")
    private Long universityId;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Degree program is required")
    private String degreeProgram;

    @NotNull(message = "Semester is required")
    private Integer semester;

    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    @NotBlank(message = "Language is required")
    private String language;

    @NotBlank(message = "Available time is required")
    private String availableTime;

    @NotNull(message = "Study mode is required")
    private StudyMode studyMode;

    @NotNull(message = "Learning style is required")
    private LearningStyle learningStyle;

    @NotNull(message = "Learning goal is required")
    private LearningGoal learningGoal;

    @NotNull(message = "Study frequency is required")
    private StudyFrequency studyFrequency;

    // getters and setters
}