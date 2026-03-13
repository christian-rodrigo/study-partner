package com.example.backend.repository;

import com.example.backend.entity.Course;
import com.example.backend.entity.TutorCourse;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long> {
    boolean existsByTutorAndCourse(User tutor, Course course);

    List<TutorCourse> findByTutor(User tutor);
}
