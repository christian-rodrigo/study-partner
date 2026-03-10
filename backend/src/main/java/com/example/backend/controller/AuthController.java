package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setUniversity(req.getUniversity());
        user.setCity(req.getCity());
        user.setDegreeProgram(req.getDegreeProgram());
        user.setSemester(req.getSemester());
        user.setBio(req.getBio());

        User saved = userRepository.save(user);

        UserResponse res = new UserResponse();
        res.setId(saved.getId());
        res.setEmail(saved.getEmail());
        res.setName(saved.getName());
        res.setUniversity(saved.getUniversity());
        res.setCity(saved.getCity());
        res.setDegreeProgram(saved.getDegreeProgram());
        res.setSemester(saved.getSemester());
        res.setBio(saved.getBio());

        return res;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return "login ok";
    }

    @GetMapping("/me")
    public String me() {
        return "current user (JWT later)";
    }
}