package com.example.backend.dto;

import com.example.backend.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setName(user.getName());

        if (user.getUniversity() != null) {
            res.setUniversityId(user.getUniversity().getId());
            res.setUniversityName(user.getUniversity().getName());
        }

        res.setCity(user.getCity());
        res.setDegreeProgram(user.getDegreeProgram());
        res.setSemester(user.getSemester());
        res.setBio(user.getBio());

        res.setLanguage(user.getLanguage());
        res.setAvailableTime(user.getAvailableTime());
        res.setStudyMode(user.getStudyMode());

        res.setLearningStyle(user.getLearningStyle());
        res.setLearningGoal(user.getLearningGoal());
        res.setStudyFrequency(user.getStudyFrequency());
        res.setAvatarSeed(user.getAvatarSeed());

        return res;
    }
}