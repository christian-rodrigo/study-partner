package com.example.backend.service;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {

    @Autowired
    private UserRepository userRepository;

    public List<DiscoverUserDTO> getRecommendations(User currentUser) {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(user -> {
                    int percentage = calculateMatchPercentage(currentUser, user);
                    return new DiscoverUserDTO(user, percentage);
                })
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(20)
                .toList();
    }

    public int calculateMatchPercentage(User currentUser, User otherUser) {
        int maxScore = 135;
        int score = calculateScore(currentUser, otherUser);
        return (score * 100) / maxScore;
    }

    private int calculateScore(User currentUser, User otherUser) {
        int score = 0;

        if (equalsIgnoreCase(currentUser.getDegreeProgram(), otherUser.getDegreeProgram())) {
            score += 30;
        }

        if (sameUniversity(currentUser, otherUser)) {
            score += 15;
        }

        if (equalsIgnoreCase(currentUser.getCity(), otherUser.getCity())) {
            score += 10;
        }

        if (isSimilarSemester(currentUser, otherUser)) {
            score += 15;
        }

        if (equalsIgnoreCase(currentUser.getAvailableTime(), otherUser.getAvailableTime())) {
            score += 20;
        }

        if (currentUser.getStudyMode() != null && currentUser.getStudyMode() == otherUser.getStudyMode()) {
            score += 10;
        }

        if (equalsIgnoreCase(currentUser.getLanguage(), otherUser.getLanguage())) {
            score += 10;
        }

        if (currentUser.getLearningStyle() != null && currentUser.getLearningStyle() == otherUser.getLearningStyle()) {
            score += 10;
        }

        return score;
    }

    private boolean equalsIgnoreCase(String a, String b) {
        return a != null && a.equalsIgnoreCase(b);
    }

    private boolean sameUniversity(User a, User b) {
        return a.getUniversity() != null
                && b.getUniversity() != null
                && equalsIgnoreCase(a.getUniversity().getName(), b.getUniversity().getName());
    }

    private boolean isSimilarSemester(User a, User b) {
        return a.getSemester() != null
                && b.getSemester() != null
                && Math.abs(a.getSemester() - b.getSemester()) <= 1;
    }
}