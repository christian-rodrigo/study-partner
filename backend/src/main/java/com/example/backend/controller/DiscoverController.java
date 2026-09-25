package com.example.backend.controller;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.matching.MatchingStrategyType;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<DiscoverUserDTO> getDiscoverUsers(
            Authentication authentication,
            @RequestParam(defaultValue = "weighted") String strategy) {

        User currentUser = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MatchingStrategyType strategyType;

        try {
            strategyType = MatchingStrategyType.valueOf(
                    strategy.toUpperCase(Locale.ROOT)
            );
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Unknown matching strategy: " + strategy
            );
        }

        return discoverService.getRecommendations(
                currentUser,
                strategyType
        );
    }
}