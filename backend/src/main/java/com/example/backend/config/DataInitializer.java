package com.example.backend.config;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.LearningGoal;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.LanguageLevel;
import com.example.backend.enums.StudyFrequency;
import com.example.backend.enums.StudyMode;
import com.example.backend.enums.UserType;
import com.example.backend.repository.UniversityRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadUniversities(
            PasswordEncoder passwordEncoder,
            UniversityRepository universityRepository,
            UserRepository userRepository
    ) {
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

                createUser(
                        userRepository, passwordEncoder,
                        "student1@example.com", "Student 1", "Dortmund", "Computer Science", 3, u3,
                        "German", "Evening", StudyMode.BOTH, LearningStyle.QUIET,
                        LearningGoal.EXAM_PREPARATION, StudyFrequency.TWICE_A_WEEK,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student2@example.com", "Student 2", "Dortmund", "Computer Science", 3, u3,
                        "German", "Evening", StudyMode.BOTH, LearningStyle.QUIET,
                        LearningGoal.EXAM_PREPARATION, StudyFrequency.TWICE_A_WEEK,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student3@example.com", "Student 3", "Dortmund", "Computer Science", 4, u3,
                        "German", "Evening", StudyMode.BOTH, LearningStyle.GROUP,
                        LearningGoal.REGULAR_STUDY, StudyFrequency.TWICE_A_WEEK,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student4@example.com", "Student 4", "Dortmund", "Data Science", 3, u3,
                        "German", "Evening", StudyMode.ONLINE, LearningStyle.QUIET,
                        LearningGoal.EXAM_PREPARATION, StudyFrequency.BEFORE_EXAMS,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student5@example.com", "Student 5", "Berlin", "Computer Science", 3, u3,
                        "English", "Morning", StudyMode.ONLINE, LearningStyle.GROUP,
                        LearningGoal.HOMEWORK, StudyFrequency.DAILY,
                        LanguageLevel.C1
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student6@example.com", "Student 6", "Darmstadt", "Mechanical Engineering", 5, u4,
                        "German", "Afternoon", StudyMode.OFFLINE, LearningStyle.QUIET,
                        LearningGoal.REGULAR_STUDY, StudyFrequency.WEEKENDS,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student7@example.com", "Student 7", "Darmstadt", "Electrical Engineering", 5, u4,
                        "German", "Afternoon", StudyMode.OFFLINE, LearningStyle.GROUP,
                        LearningGoal.HOMEWORK, StudyFrequency.WEEKENDS,
                        LanguageLevel.B1
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student8@example.com", "Student 8", "Gießen", "Psychology", 2, u2,
                        "German", "Morning", StudyMode.BOTH, LearningStyle.QUIET,
                        LearningGoal.REGULAR_STUDY, StudyFrequency.DAILY,
                        LanguageLevel.C1
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student9@example.com", "Student 9", "Gießen", "Mathematics", 2, u2,
                        "German", "Morning", StudyMode.BOTH, LearningStyle.EXAM_FOCUSED,
                        LearningGoal.EXAM_PREPARATION, StudyFrequency.DAILY,
                        LanguageLevel.C1
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student10@example.com", "Student 10", "Dortmund", "Business Administration", 1, u3,
                        "German", "Evening", StudyMode.OFFLINE, LearningStyle.GROUP,
                        LearningGoal.HOMEWORK, StudyFrequency.TWICE_A_WEEK,
                        LanguageLevel.B2
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student11@example.com", "Student 11", "Hamburg", "Computer Science", 6, u1,
                        "English", "Night", StudyMode.ONLINE, LearningStyle.EXAM_FOCUSED,
                        LearningGoal.EXAM_PREPARATION, StudyFrequency.BEFORE_EXAMS,
                        LanguageLevel.C1
                );

                createUser(
                        userRepository, passwordEncoder,
                        "student12@example.com", "Student 12", "Munich", "Data Science", 4, u1,
                        "English", "Evening", StudyMode.BOTH, LearningStyle.QUIET,
                        LearningGoal.REGULAR_STUDY, StudyFrequency.TWICE_A_WEEK,
                        LanguageLevel.B2
                );
            }
        };
    }

    private void createUser(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            String email,
            String name,
            String city,
            String degreeProgram,
            int semester,
            University university,
            String language,
            String availableTime,
            StudyMode studyMode,
            LearningStyle learningStyle,
            LearningGoal learningGoal,
            StudyFrequency studyFrequency,
            LanguageLevel languageLevel
    ) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setName(name);
        user.setAge(20 + (semester % 6));
        user.setCity(city);
        user.setDegreeProgram(degreeProgram);
        user.setSemester(semester);
        user.setUniversity(university);
        user.setLanguage(language);
        user.setAvailableTime(availableTime);
        user.setStudyMode(studyMode);
        user.setLearningStyle(learningStyle);
        user.setLearningGoal(learningGoal);
        user.setStudyFrequency(studyFrequency);
        user.setLanguageLevel(languageLevel);
        user.setRole(UserType.STUDENT);
        user.setAvatarSeed(UUID.randomUUID().toString());

        userRepository.save(user);
    }
}

