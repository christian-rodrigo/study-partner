package com.example.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddTutorCoursesRequest {
    private List<Long> courseIds;          // Courses selected from existing list
    private List<String> newCourseNames;   // Courses typed manually by tutor
}
