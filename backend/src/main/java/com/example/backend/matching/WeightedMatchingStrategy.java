package com.example.backend.matching;

import com.example.backend.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;
@Component
@Primary
public class WeightedMatchingStrategy implements MatchingStrategy {

    private static final int MAXIMUM_SCORE = 120;

    @Override
    public MatchResult calculate(User currentUser, User candidate) {

        int score = 0;

        if (equalsIgnoreCase(
                currentUser.getDegreeProgram(),
                candidate.getDegreeProgram())) {
            score += 30;
        }

        if (sameUniversity(currentUser, candidate)) {
            score += 15;
        }

        if (equalsIgnoreCase(
                currentUser.getCity(),
                candidate.getCity())) {
            score += 10;
        }

        if (isSimilarSemester(currentUser, candidate)) {
            score += 15;
        }

        if (equalsIgnoreCase(
                currentUser.getAvailableTime(),
                candidate.getAvailableTime())) {
            score += 20;
        }

        if (currentUser.getStudyMode() != null
                && currentUser.getStudyMode() == candidate.getStudyMode()) {
            score += 10;
        }

        if (equalsIgnoreCase(
                currentUser.getLanguage(),
                candidate.getLanguage())) {
            score += 10;
        }

        if (currentUser.getLearningStyle() != null
                && currentUser.getLearningStyle() == candidate.getLearningStyle()) {
            score += 10;
        }

        int percentage = (score * 100) / MAXIMUM_SCORE;

        return new MatchResult(
                score,
                MAXIMUM_SCORE,
                percentage
        );
    }

    private boolean equalsIgnoreCase(String first, String second) {
        return first != null
                && second != null
                && first.equalsIgnoreCase(second);
    }

    private boolean sameUniversity(User first, User second) {
        return first.getUniversity() != null
                && second.getUniversity() != null
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