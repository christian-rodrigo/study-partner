package com.example.backend.controller;

import com.example.backend.dto.DiscoverUserDTO;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<DiscoverUserDTO> getDiscoverUsers(@PathVariable Long userId) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return discoverService.getRecommendations(currentUser);
    }
}