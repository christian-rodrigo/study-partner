package com.example.backend.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String university;
    private String city;
    private String degreeProgram;
    private Integer semester;
    private String bio;
}
