package com.example.backend.controller;

import com.example.backend.dto.*;
import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.repository.UniversityRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UniversityRepository universityRepository;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          UniversityRepository universityRepository,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.universityRepository = universityRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        University university = universityRepository
                .findById(req.getUniversityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "University not found"));

        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        user.setUniversity(university);
        user.setCity(req.getCity());
        user.setDegreeProgram(req.getDegreeProgram());
        user.setSemester(req.getSemester());
        user.setBio(req.getBio());

        user.setLanguage(req.getLanguage());
        user.setAvailableTime(req.getAvailableTime());
        user.setStudyMode(req.getStudyMode());

        user.setLearningStyle(req.getLearningStyle());
        user.setLearningGoal(req.getLearningGoal());
        user.setStudyFrequency(req.getStudyFrequency());
        user.setRole(req.getUserType());

        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, UserMapper.toResponse(user));
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return UserMapper.toResponse(user);
    }

}