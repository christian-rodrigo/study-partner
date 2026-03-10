package com.example.backend.controller;

import com.example.backend.dto.UserResponse;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication){
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return userService.toUserResponse(user);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
       return userService.getAllUsers();
    }
}
