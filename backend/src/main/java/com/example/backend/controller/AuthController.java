package com.example.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "register endpoint";
    }

    @PostMapping("/login")
    public String login() {
        return "login endpoint";
    }

    @GetMapping("/me")
    public String me() {
        return "current user";
    }
}