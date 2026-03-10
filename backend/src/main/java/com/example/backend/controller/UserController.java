package com.example.backend.controller;

import com.example.backend.dto.UserResponse;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search")
    public List<UserResponse> searchUsers(
            @RequestParam(required = false) String university,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String degreeProgram,
            @RequestParam(required = false) Integer semester
    ) {
        return userService.searchUsers(university, city, degreeProgram, semester);
    }
}