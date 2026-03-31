package com.example.backend.specification;

import com.example.backend.dto.UserSearchFilter;
import com.example.backend.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> withFilters(UserSearchFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getUniversityId() != null) {
                predicates.add(
                        cb.equal(root.get("university").get("id"), filter.getUniversityId())
                );
            }

            if (filter.getCity() != null && !filter.getCity().isBlank()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("city")), filter.getCity().toLowerCase())
                );
            }

            if (filter.getDegreeProgram() != null && !filter.getDegreeProgram().isBlank()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("degreeProgram")), filter.getDegreeProgram().toLowerCase())
                );
            }

            if (filter.getSemester() != null) {
                predicates.add(
                        cb.equal(root.get("semester"), filter.getSemester())
                );
            }

            if (filter.getLanguage() != null && !filter.getLanguage().isBlank()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("language")), filter.getLanguage().toLowerCase())
                );
            }

            if (filter.getAvailableTime() != null && !filter.getAvailableTime().isBlank()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("availableTime")), filter.getAvailableTime().toLowerCase())
                );
            }

            if (filter.getStudyMode() != null) {
                predicates.add(
                        cb.equal(root.get("studyMode"), filter.getStudyMode())
                );
            }

            if (filter.getLearningStyle() != null) {
                predicates.add(
                        cb.equal(root.get("learningStyle"), filter.getLearningStyle())
                );
            }

            if (filter.getLearningGoal() != null) {
                predicates.add(
                        cb.equal(root.get("learningGoal"), filter.getLearningGoal())
                );
            }

            if (filter.getStudyFrequency() != null) {
                predicates.add(
                        cb.equal(root.get("studyFrequency"), filter.getStudyFrequency())
                );
            }

            if (filter.getMinAge() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("age"), filter.getMinAge())
                );
            }

            if (filter.getMaxAge() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("age"), filter.getMaxAge())
                );
            }

            if (filter.getKeyword() != null && !filter.getKeyword().isBlank()) {
                String keyword = "%" + filter.getKeyword().toLowerCase() + "%";

                Predicate bioLike = cb.like(cb.lower(root.get("bio")), keyword);
                Predicate degreeLike = cb.like(cb.lower(root.get("degreeProgram")), keyword);

                predicates.add(cb.or(bioLike, degreeLike));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}