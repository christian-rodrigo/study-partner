package com.example.backend.service;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.matching.MatchResult;
import com.example.backend.matching.MatchingStrategy;
import com.example.backend.matching.MatchingStrategyResolver;
import com.example.backend.matching.MatchingStrategyType;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoverService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchingStrategyResolver matchingStrategyResolver;

    // Bestehendes Verhalten bleibt erhalten:
    // Ohne explizite Auswahl wird WEIGHTED verwendet.
    public List<DiscoverUserDTO> getRecommendations(User currentUser) {
        return getRecommendations(
                currentUser,
                MatchingStrategyType.WEIGHTED
        );
    }

    // Neue Variante mit auswählbarer Matching-Strategie
    public List<DiscoverUserDTO> getRecommendations(
            User currentUser,
            MatchingStrategyType strategyType) {

        MatchingStrategy matchingStrategy =
                matchingStrategyResolver.resolve(strategyType);

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(user -> {

                    MatchResult result =
                            matchingStrategy.calculate(currentUser, user);

                    return new DiscoverUserDTO(
                            user,
                            result.percentage()
                    );
                })
                .sorted((a, b) ->
                        Integer.compare(
                                b.getScore(),
                                a.getScore()
                        ))
                .limit(20)
                .toList();
    }

    // Bestehende Methode bleibt kompatibel
    public int calculateMatchPercentage(
            User currentUser,
            User otherUser) {

        return calculateMatchPercentage(
                currentUser,
                otherUser,
                MatchingStrategyType.WEIGHTED
        );
    }

    // Neue Variante mit auswählbarer Strategie
    public int calculateMatchPercentage(
            User currentUser,
            User otherUser,
            MatchingStrategyType strategyType) {

        MatchingStrategy matchingStrategy =
                matchingStrategyResolver.resolve(strategyType);

        return matchingStrategy
                .calculate(currentUser, otherUser)
                .percentage();
    }
}