package com.example.backend.matching;

import com.example.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class WeightedMatchingStrategy implements MatchingStrategy {

    private static final int MAXIMUM_SCORE = 120;

    @Override
    public MatchResult calculate(User currentUser, User candidate) {

        int score = 0;
        int availableMaximumScore = 0;

        // Studiengang
        if (bothAvailable(
                currentUser.getDegreeProgram(),
                candidate.getDegreeProgram())) {

            availableMaximumScore += 30;

            if (equalsIgnoreCase(
                    currentUser.getDegreeProgram(),
                    candidate.getDegreeProgram())) {
                score += 30;
            }
        }

        // Hochschule
        if (universitiesAvailable(currentUser, candidate)) {

            availableMaximumScore += 15;

            if (sameUniversity(currentUser, candidate)) {
                score += 15;
            }
        }

        // Stadt
        if (bothAvailable(
                currentUser.getCity(),
                candidate.getCity())) {

            availableMaximumScore += 10;

            if (equalsIgnoreCase(
                    currentUser.getCity(),
                    candidate.getCity())) {
                score += 10;
            }
        }

        // Semester
        if (currentUser.getSemester() != null
                && candidate.getSemester() != null) {

            availableMaximumScore += 15;

            if (isSimilarSemester(currentUser, candidate)) {
                score += 15;
            }
        }

        // Verfügbare Lernzeit
        if (bothAvailable(
                currentUser.getAvailableTime(),
                candidate.getAvailableTime())) {

            availableMaximumScore += 20;

            if (equalsIgnoreCase(
                    currentUser.getAvailableTime(),
                    candidate.getAvailableTime())) {
                score += 20;
            }
        }

        // Lernform
        if (currentUser.getStudyMode() != null
                && candidate.getStudyMode() != null) {

            availableMaximumScore += 10;

            if (currentUser.getStudyMode() == candidate.getStudyMode()) {
                score += 10;
            }
        }

        // Sprache
        if (bothAvailable(
                currentUser.getLanguage(),
                candidate.getLanguage())) {

            availableMaximumScore += 10;

            if (equalsIgnoreCase(
                    currentUser.getLanguage(),
                    candidate.getLanguage())) {
                score += 10;
            }
        }

        // Lernstil
        if (currentUser.getLearningStyle() != null
                && candidate.getLearningStyle() != null) {

            availableMaximumScore += 10;

            if (currentUser.getLearningStyle()
                    == candidate.getLearningStyle()) {
                score += 10;
            }
        }

        int percentage = (score * 100) / MAXIMUM_SCORE;

        double informationCoverage =
                (availableMaximumScore * 100.0) / MAXIMUM_SCORE;

        return new MatchResult(
                score,
                MAXIMUM_SCORE,
                percentage,
                informationCoverage
        );
    }



    private boolean bothAvailable(String first, String second) {
        return first != null && !first.isBlank()
                && second != null && !second.isBlank();
    }


    private boolean equalsIgnoreCase(String first, String second) {
        return first != null
                && second != null
                && first.equalsIgnoreCase(second);
    }


    private boolean universitiesAvailable(User first, User second) {
        return first.getUniversity() != null
                && second.getUniversity() != null
                && first.getUniversity().getName() != null
                && second.getUniversity().getName() != null;
    }


    private boolean sameUniversity(User first, User second) {
        return universitiesAvailable(first, second)
                && equalsIgnoreCase(
                first.getUniversity().getName(),
                second.getUniversity().getName()
        );
    }


    private boolean isSimilarSemester(User first, User second) {
        return first.getSemester() != null
                && second.getSemester() != null
                && Math.abs(
                first.getSemester() - second.getSemester()
        ) <= 1;
    }
}