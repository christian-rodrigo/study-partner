package com.example.backend.service;

import com.example.backend.dto.UpdateUserProfileRequest;
import com.example.backend.dto.UserResponse;
import com.example.backend.dto.UserSearchFilter;
import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.repository.UniversityRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.specification.UserSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    public UserService(UserRepository userRepository, UniversityRepository universityRepository) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<UserResponse> searchUsers(UserSearchFilter filter) {
        return userRepository.findAll(UserSpecification.withFilters(filter))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        if (user.getUniversity() != null) {
            response.setUniversityId(user.getUniversity().getId());
            response.setUniversityName(user.getUniversity().getName());
        }

        response.setCity(user.getCity());
        response.setDegreeProgram(user.getDegreeProgram());
        response.setSemester(user.getSemester());
        response.setBio(user.getBio());
        response.setLanguage(user.getLanguage());
        response.setAvailableTime(user.getAvailableTime());
        response.setStudyMode(user.getStudyMode());
        response.setLearningStyle(user.getLearningStyle());
        response.setLearningGoal(user.getLearningGoal());
        response.setStudyFrequency(user.getStudyFrequency());

        return response;
    }

    public UserResponse updateUserProfile(Long userId, UpdateUserProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found"));

        user.setName(request.getName());
        user.setUniversity(university);
        user.setCity(request.getCity());
        user.setDegreeProgram(request.getDegreeProgram());
        user.setSemester(request.getSemester());
        user.setBio(request.getBio());
        user.setLanguage(request.getLanguage());
        user.setAvailableTime(request.getAvailableTime());
        user.setStudyMode(request.getStudyMode());
        user.setLearningStyle(request.getLearningStyle());
        user.setLearningGoal(request.getLearningGoal());
        user.setStudyFrequency(request.getStudyFrequency());

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public UserResponse updateUserProfileByEmail(String email, UpdateUserProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return updateUserProfile(user.getId(), request);
    }
}