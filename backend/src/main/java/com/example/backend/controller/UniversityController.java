package com.example.backend.controller;

import com.example.backend.entity.University;
import com.example.backend.repository.UniversityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityRepository universityRepository;

    public UniversityController(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @GetMapping
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}