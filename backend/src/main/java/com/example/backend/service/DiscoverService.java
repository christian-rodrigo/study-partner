package com.example.backend.service;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DiscoverService {

    @Autowired
    private UserRepository userRepository;

    public List<DiscoverUserDTO> getRecommendations(User currentUser) {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(user -> {
                    int score = calculateScore(currentUser, user);
                    return new DiscoverUserDTO(user, score);
                })
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(20)
                .toList();
    }

    private int calculateScore(User currentUser, User otherUser) {
        int score = 0;

        if (equals(currentUser.getDegreeProgram(), otherUser.getDegreeProgram())) {
            score += 40;
        }

        if (currentUser.getUniversity() != null && otherUser.getUniversity() != null
                && equals(currentUser.getUniversity().getName(), otherUser.getUniversity().getName())) {
            score += 20;
        }

        if (equals(currentUser.getCity(), otherUser.getCity())) {
            score += 20;
        }

        if (equals(currentUser.getAvailableTime(), otherUser.getAvailableTime())) {
            score += 15;
        }

        return score;
    }

    private boolean equals(String a, String b) {
        return a != null && b != null && a.equalsIgnoreCase(b);
    }
}