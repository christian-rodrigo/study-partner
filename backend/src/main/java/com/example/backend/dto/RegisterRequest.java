package com.example.backend.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String university;
    private String city;
    private String degreeProgram;
    private Integer semester;
    private String bio;
}