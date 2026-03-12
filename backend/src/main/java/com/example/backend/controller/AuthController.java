package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.repository.UniversityRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UniversityRepository universityRepository;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          UniversityRepository universityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.universityRepository = universityRepository;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = new User();
        University university = universityRepository.findById(req.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found"));
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

        User saved = userRepository.save(user);

        UserResponse res = new UserResponse();
        res.setId(saved.getId());
        res.setEmail(saved.getEmail());
        res.setName(saved.getName());

        res.setUniversityId(user.getUniversity().getId());
        res.setUniversityName(user.getUniversity().getName());
        res.setCity(saved.getCity());
        res.setDegreeProgram(saved.getDegreeProgram());
        res.setSemester(saved.getSemester());
        res.setBio(saved.getBio());

        res.setLanguage(saved.getLanguage());
        res.setAvailableTime(saved.getAvailableTime());
        res.setStudyMode(saved.getStudyMode());

        res.setLearningStyle(saved.getLearningStyle());
        res.setLearningGoal(saved.getLearningGoal());
        res.setStudyFrequency(saved.getStudyFrequency());

        return res;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return "login ok";
    }

    @GetMapping("/me")
    public String me() {
        return "current user (JWT later)";
    }
}