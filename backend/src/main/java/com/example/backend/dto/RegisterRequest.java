package com.example.backend.dto;

import com.example.backend.entity.LearningGoal;
import com.example.backend.entity.LearningStyle;
import com.example.backend.entity.StudyFrequency;
import com.example.backend.entity.StudyMode;
import com.example.backend.enums.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password must have at least 4 characters")
    private String password;

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

    private UserType userType;
}