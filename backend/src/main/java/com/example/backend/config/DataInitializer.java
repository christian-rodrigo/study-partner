package com.example.backend.config;

import com.example.backend.entity.University;
import com.example.backend.repository.UniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadUniversities(UniversityRepository universityRepository) {
        return args -> {
            if (universityRepository.count() == 0) {
                University u1 = new University();
                u1.setName("THM");

                University u2 = new University();
                u2.setName("Universität Gießen");

                University u3 = new University();
                u3.setName("FH Dortmund");

                University u4 = new University();
                u4.setName("TU Darmstadt");

                universityRepository.save(u1);
                universityRepository.save(u2);
                universityRepository.save(u3);
                universityRepository.save(u4);
            }
        };
    }
}