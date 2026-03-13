package com.example.backend.controller;

import com.example.backend.dto.UserResponse;
import com.example.backend.dto.UserSearchFilter;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.UpdateUserProfileRequest;
import jakarta.validation.Valid;

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

    @PostMapping("/search")
    public List<UserResponse> searchUsers(@RequestBody UserSearchFilter filter) {
        return userService.searchUsers(filter);
    }
    @PutMapping("/me")
    public UserResponse updateUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserProfileRequest request
    ) {
        return userService.updateUserProfile(id, request);
    }
}