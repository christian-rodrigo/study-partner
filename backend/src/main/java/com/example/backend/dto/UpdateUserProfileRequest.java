package com.example.backend.dto;

import com.example.backend.enums.LearningGoal;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyFrequency;
import com.example.backend.enums.StudyMode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public StudyMode getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(StudyMode studyMode) {
        this.studyMode = studyMode;
    }

    public LearningStyle getLearningStyle() {
        return learningStyle;
    }

    public void setLearningStyle(LearningStyle learningStyle) {
        this.learningStyle = learningStyle;
    }

    public LearningGoal getLearningGoal() {
        return learningGoal;
    }

    public void setLearningGoal(LearningGoal learningGoal) {
        this.learningGoal = learningGoal;
    }

    public StudyFrequency getStudyFrequency() {
        return studyFrequency;
    }

    public void setStudyFrequency(StudyFrequency studyFrequency) {
        this.studyFrequency = studyFrequency;
    }
}