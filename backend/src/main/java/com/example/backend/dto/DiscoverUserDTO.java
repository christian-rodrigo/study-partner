package com.example.backend.dto;

import com.example.backend.entity.User;
import lombok.Getter;

@Getter
public class DiscoverUserDTO {
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
    private String studyMode;
    private String learningStyle;
    private String learningGoal;
    private String studyFrequency;
    private int score;
    private String avatarSeed;

    public DiscoverUserDTO(User user, int score) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.universityId = user.getUniversity() != null ? user.getUniversity().getId() : null;
        this.universityName = user.getUniversity() != null ? user.getUniversity().getName() : null;
        this.city = user.getCity();
        this.degreeProgram = user.getDegreeProgram();
        this.semester = user.getSemester();
        this.bio = user.getBio();
        this.language = user.getLanguage();
        this.availableTime = user.getAvailableTime();
        this.studyMode = user.getStudyMode() != null ? user.getStudyMode().name() : null;
        this.learningStyle = user.getLearningStyle() != null ? user.getLearningStyle().name() : null;
        this.learningGoal = user.getLearningGoal() != null ? user.getLearningGoal().name() : null;
        this.studyFrequency = user.getStudyFrequency() != null ? user.getStudyFrequency().name() : null;
        this.score = score;
        this.avatarSeed = user.getAvatarSeed();
    }

}