package com.example.backend.dto;

import com.example.backend.enums.LearningGoal;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyFrequency;
import com.example.backend.enums.StudyMode;
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

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}