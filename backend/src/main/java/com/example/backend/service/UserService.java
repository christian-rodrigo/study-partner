package com.example.backend.service;

import com.example.backend.dto.UserResponse;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();

        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<UserResponse> searchUsers(String university, String city, String degreeProgram, Integer semester) {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> university == null || user.getUniversity() != null &&
                        user.getUniversity().equalsIgnoreCase(university))
                .filter(user -> city == null || user.getCity() != null &&
                        user.getCity().equalsIgnoreCase(city))
                .filter(user -> degreeProgram == null || user.getDegreeProgram() != null &&
                        user.getDegreeProgram().equalsIgnoreCase(degreeProgram))
                .filter(user -> semester == null || user.getSemester() != null &&
                        user.getSemester().equals(semester))
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setUniversity(user.getUniversity());
        response.setCity(user.getCity());
        response.setDegreeProgram(user.getDegreeProgram());
        response.setSemester(user.getSemester());
        response.setBio(user.getBio());
        return response;
    }
}