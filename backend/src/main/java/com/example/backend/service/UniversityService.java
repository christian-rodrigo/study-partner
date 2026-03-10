package com.example.backend.service;

import com.example.backend.entity.University;
import com.example.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getAllUniversities(){
        return universityRepository.findAll();
    }



}
