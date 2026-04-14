package com.example.backend.config;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.UserType;
import com.example.backend.repository.UniversityRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadUniversities(PasswordEncoder passwordEncoder, UniversityRepository universityRepository, UserRepository userRepository) {
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


                String[] degreePrograms = {
                        "Computer Science",
                        "Business Administration",
                        "Mechanical Engineering",
                        "Electrical Engineering",
                        "Psychology",
                        "Mathematics",
                        "Data Science"
                };

                for (int i = 1; i <= 5; i++) {
                    User user = new User();

                    user.setEmail("user" + i + "@example.com");
                    user.setPassword(passwordEncoder.encode("1234"));
                    user.setName("Student " + i);
                    user.setRole(i % 2 == 0 ? UserType.TUTOR : UserType.STUDENT);
                    user.setUniversity(i % 2 == 0 ? u4 : u3);
                    user.setCity("Dortmund");

                    String degree = degreePrograms[(int) (Math.random() * degreePrograms.length)];
                    user.setDegreeProgram(degree);

                    user.setSemester((i % 6) + 1);

                    userRepository.save(user);
                }
            }
        };
    }
}