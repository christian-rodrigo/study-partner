package com.example.backend.controller;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<DiscoverUserDTO> getDiscoverUsers(Authentication authentication) {
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return discoverService.getRecommendations(currentUser);
    }
}