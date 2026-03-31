package com.example.backend.dto;

import com.example.backend.entity.LearningGoal;
import com.example.backend.entity.LearningStyle;
import com.example.backend.entity.StudyFrequency;
import com.example.backend.entity.StudyMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UserSearchFilter {

    private Long universityId;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Size(max = 100, message = "Degree program must not exceed 100 characters")
    private String degreeProgram;

    private Integer semester;

    @Size(max = 50, message = "Language must not exceed 50 characters")
    private String language;

    @Size(max = 100, message = "Available time must not exceed 100 characters")
    private String availableTime;

    private StudyMode studyMode;
    private LearningStyle learningStyle;
    private LearningGoal learningGoal;
    private StudyFrequency studyFrequency;

    @Min(value = 1, message = "Min age must be at least 1")
    @Max(value = 100, message = "Min age must be at most 100")
    private Integer minAge;

    @Min(value = 1, message = "Max age must be at least 1")
    @Max(value = 100, message = "Max age must be at most 100")
    private Integer maxAge;

    @Size(max = 100, message = "Keyword must not exceed 100 characters")
    private String keyword;

    // getters and setters
}