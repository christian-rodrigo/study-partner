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

            if (request.getUniversityId() != null) {
                University university = universityRepository.findById(request.getUniversityId())
                        .orElseThrow(() -> new RuntimeException("University not found"));
                user.setUniversity(university);
            }

            if (request.getName() != null) {
                user.setName(request.getName());
            }
            if (request.getCity() != null) {
                user.setCity(request.getCity());
            }
            if (request.getDegreeProgram() != null) {
                user.setDegreeProgram(request.getDegreeProgram());
            }
            if (request.getSemester() != null) {
                user.setSemester(request.getSemester());
            }
            if (request.getBio() != null) {
                user.setBio(request.getBio());
            }
            if (request.getLanguage() != null) {
                user.setLanguage(request.getLanguage());
            }
            if (request.getAvailableTime() != null) {
                user.setAvailableTime(request.getAvailableTime());
            }
            if (request.getStudyMode() != null) {
                user.setStudyMode(request.getStudyMode());
            }
            if (request.getLearningStyle() != null) {
                user.setLearningStyle(request.getLearningStyle());
            }
            if (request.getLearningGoal() != null) {
                user.setLearningGoal(request.getLearningGoal());
            }
            if (request.getStudyFrequency() != null) {
                user.setStudyFrequency(request.getStudyFrequency());
            }

            User savedUser = userRepository.save(user);
            return mapToResponse(savedUser);
        }


    public UserResponse updateUserProfileByEmail(String email, UpdateUserProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return updateUserProfile(user.getId(), request);
    }
}