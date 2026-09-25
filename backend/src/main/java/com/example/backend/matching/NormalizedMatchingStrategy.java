package com.example.backend.matching;

import com.example.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NormalizedMatchingStrategy implements MatchingStrategy {

    @Override
    public MatchResult calculate(User currentUser, User candidate) {

        int score = 0;
        int availableMaximumScore = 0;

        // 1. Studiengang: 30 Punkte
        if (hasText(currentUser.getDegreeProgram())
                && hasText(candidate.getDegreeProgram())) {

            availableMaximumScore += 30;

            if (equalsIgnoreCase(
                    currentUser.getDegreeProgram(),
                    candidate.getDegreeProgram())) {

                score += 30;
            }
        }

        // 2. Hochschule: 15 Punkte
        if (hasUniversity(currentUser)
                && hasUniversity(candidate)) {

            availableMaximumScore += 15;

            if (sameUniversity(currentUser, candidate)) {
                score += 15;
            }
        }

        // 3. Stadt: 10 Punkte
        if (hasText(currentUser.getCity())
                && hasText(candidate.getCity())) {

            availableMaximumScore += 10;

            if (equalsIgnoreCase(
                    currentUser.getCity(),
                    candidate.getCity())) {

                score += 10;
            }
        }

        // 4. Semester: 15 Punkte
        if (currentUser.getSemester() != null
                && candidate.getSemester() != null) {

            availableMaximumScore += 15;

            if (isSimilarSemester(currentUser, candidate)) {
                score += 15;
            }
        }

        // 5. Verfügbare Lernzeit: 20 Punkte
        if (hasText(currentUser.getAvailableTime())
                && hasText(candidate.getAvailableTime())) {

            availableMaximumScore += 20;

            if (equalsIgnoreCase(
                    currentUser.getAvailableTime(),
                    candidate.getAvailableTime())) {

                score += 20;
            }
        }

        // 6. Lernform: 10 Punkte
        if (currentUser.getStudyMode() != null
                && candidate.getStudyMode() != null) {

            availableMaximumScore += 10;

            if (currentUser.getStudyMode() == candidate.getStudyMode()) {
                score += 10;
            }
        }

        // 7. Sprache: 10 Punkte
        if (hasText(currentUser.getLanguage())
                && hasText(candidate.getLanguage())) {

            availableMaximumScore += 10;

            if (equalsIgnoreCase(
                    currentUser.getLanguage(),
                    candidate.getLanguage())) {

                score += 10;
            }
        }

        // 8. Lernstil: 10 Punkte
        if (currentUser.getLearningStyle() != null
                && candidate.getLearningStyle() != null) {

            availableMaximumScore += 10;

            if (currentUser.getLearningStyle()
                    == candidate.getLearningStyle()) {

                score += 10;
            }
        }

        int percentage = availableMaximumScore == 0
                ? 0
                : (score * 100) / availableMaximumScore;

        return new MatchResult(
                score,
                availableMaximumScore,
                percentage
        );
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    private boolean equalsIgnoreCase(String first, String second) {
        return first.equalsIgnoreCase(second);
    }

    private boolean hasUniversity(User user) {
        return user.getUniversity() != null
                && hasText(user.getUniversity().getName());
    }

    private boolean sameUniversity(User first, User second) {
        return first.getUniversity()
                .getName()
                .equalsIgnoreCase(second.getUniversity().getName());
    }

    private boolean isSimilarSemester(User first, User second) {
        return Math.abs(
                first.getSemester() - second.getSemester()
        ) <= 1;
    }
}