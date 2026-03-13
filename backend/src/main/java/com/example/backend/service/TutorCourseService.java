package com.example.backend.service;

import com.example.backend.entity.Course;
import com.example.backend.entity.TutorCourse;
import com.example.backend.entity.User;
import com.example.backend.enums.UserType;
import com.example.backend.repository.CourseRepository;
import com.example.backend.repository.TutorCourseRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TutorCourseService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TutorCourseRepository tutorCourseRepository;

    public TutorCourseService(UserRepository userRepository,
                              CourseRepository courseRepository,
                              TutorCourseRepository tutorCourseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.tutorCourseRepository = tutorCourseRepository;
    }

    /**
     * Add courses to a tutor.
     * - courseIds: courses selected from existing courses
     * - newCourseNames: courses typed manually by tutor
     */
    public List<TutorCourse> addCoursesToTutor(Long tutorId, List<Long> courseIds, List<String> newCourseNames) {
        User tutor = userRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));

        if (!tutor.getRole().equals(UserType.TUTOR)) {
            throw new RuntimeException("Only tutors can add courses");
        }

        List<TutorCourse> addedCourses = new ArrayList<>();

        // 1️⃣ Add courses from existing IDs
        for (Long courseId : courseIds) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            boolean exists = tutorCourseRepository.existsByTutorAndCourse(tutor, course);
            if (!exists) {
                TutorCourse tc = new TutorCourse();
                tc.setTutor(tutor);
                tc.setCourse(course);
                addedCourses.add(tutorCourseRepository.save(tc));
            }
        }

        // 2️⃣ Add manually typed new courses
        for (String courseName : newCourseNames) {
            Course course = courseRepository.findByName(courseName)
                    .orElseGet(() -> {
                        // Create new course if it doesn't exist
                        Course newCourse = new Course();
                        newCourse.setName(courseName);
                        return courseRepository.save(newCourse);
                    });

            boolean exists = tutorCourseRepository.existsByTutorAndCourse(tutor, course);
            if (!exists) {
                TutorCourse tc = new TutorCourse();
                tc.setTutor(tutor);
                tc.setCourse(course);
                addedCourses.add(tutorCourseRepository.save(tc));
            }
        }

        return addedCourses;
    }

    public List<TutorCourse> findByTutor(User tutor) {
        return tutorCourseRepository.findByTutor(tutor);
    }

}