package com.example.backend.service;

import com.example.backend.dto.UserResponse;
import com.example.backend.dto.UserSearchFilter;
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

    public List<UserResponse> searchUsers(UserSearchFilter filter) {
        return userRepository.findAll()
                .stream()
                .filter(user -> filter.getUniversityId() == null ||
                        (user.getUniversity() != null &&
                                user.getUniversity().getId().equals(filter.getUniversityId())))
                .filter(user -> filter.getCity() == null ||
                        (user.getCity() != null &&
                                user.getCity().equalsIgnoreCase(filter.getCity())))
                .filter(user -> filter.getDegreeProgram() == null ||
                        (user.getDegreeProgram() != null &&
                                user.getDegreeProgram().equalsIgnoreCase(filter.getDegreeProgram())))
                .filter(user -> filter.getSemester() == null ||
                        (user.getSemester() != null &&
                                user.getSemester().equals(filter.getSemester())))
                .filter(user -> filter.getLanguage() == null ||
                        (user.getLanguage() != null &&
                                user.getLanguage().equalsIgnoreCase(filter.getLanguage())))
                .filter(user -> filter.getAvailableTime() == null ||
                        (user.getAvailableTime() != null &&
                                user.getAvailableTime().equalsIgnoreCase(filter.getAvailableTime())))
                .filter(user -> filter.getStudyMode() == null ||
                        user.getStudyMode() == filter.getStudyMode())
                .filter(user -> filter.getLearningStyle() == null ||
                        user.getLearningStyle() == filter.getLearningStyle())
                .filter(user -> filter.getLearningGoal() == null ||
                        user.getLearningGoal() == filter.getLearningGoal())
                .filter(user -> filter.getStudyFrequency() == null ||
                        user.getStudyFrequency() == filter.getStudyFrequency())
                .filter(user -> filter.getMinAge() == null || user.getAge() >= filter.getMinAge())
                .filter(user -> filter.getMaxAge() == null || user.getAge() <= filter.getMaxAge())
                .filter(user ->
                        filter.getKeyword() == null ||
                                (user.getBio() != null && user.getBio().toLowerCase().contains(filter.getKeyword().toLowerCase())) ||
                                (user.getDegreeProgram() != null && user.getDegreeProgram().toLowerCase().contains(filter.getKeyword().toLowerCase()))
                )
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
}