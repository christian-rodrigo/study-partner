package com.example.backend.controller;


import com.example.backend.dto.AddTutorCoursesRequest;
import com.example.backend.entity.Course;
import com.example.backend.entity.TutorCourse;
import com.example.backend.entity.User;
import com.example.backend.enums.UserType;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.TutorCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tutors/me/courses")
public class TutorCourseController {

    private final TutorCourseService tutorCourseService;
    private final UserRepository userRepository;

    public TutorCourseController(TutorCourseService tutorCourseService, UserRepository userRepository) {
        this.tutorCourseService = tutorCourseService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public List<TutorCourse> addCourses(
            @RequestBody AddTutorCoursesRequest request,
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getSubject(); // your JWT subject is the user's email
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return tutorCourseService.addCoursesToTutor(
                currentUser.getId(),
                request.getCourseIds(),
                request.getNewCourseNames()
        );
    }

    @GetMapping
    public List<Course> getMyCourses(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!currentUser.getRole().equals(UserType.TUTOR)) {
            throw new RuntimeException("Only tutors can have courses");
        }

        return tutorCourseService.findByTutor(currentUser)
                .stream()
                .map(TutorCourse::getCourse)
                .toList();
    }
}