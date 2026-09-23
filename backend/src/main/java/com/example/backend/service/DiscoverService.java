package com.example.backend.service;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.matching.MatchResult;
import com.example.backend.matching.MatchingStrategy;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchingStrategy matchingStrategy;

    public List<DiscoverUserDTO> getRecommendations(User currentUser) {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(user -> {
                    MatchResult result = matchingStrategy.calculate(currentUser, user);
                    int percentage = result.percentage();

                    return new DiscoverUserDTO(user, percentage);
                })
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(20)
                .toList();
    }

    public int calculateMatchPercentage(User currentUser, User otherUser) {
        return matchingStrategy
                .calculate(currentUser, otherUser)
                .percentage();
    }
}