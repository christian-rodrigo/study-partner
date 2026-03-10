package com.example.backend.service;

import com.example.backend.dto.UserResponse;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }

    public UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        return userResponse;
    }

    public UserResponse getUserResponseByEmail(String email){
        return toUserResponse(findByEmail(email));
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                    .stream()
                    .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserRepository getUserRepository(){
        return this.userRepository;
    }

}
