package com.example.backend.matching;

import com.example.backend.entity.User;
import com.example.backend.enums.StudyMode;
import org.springframework.stereotype.Component;

@Component
public class ContextSensitiveMatchingStrategy implements MatchingStrategy {

    @Override
    public MatchResult calculate(User currentUser, User candidate) {

        Weights weights = selectWeights(currentUser);

        int score = 0;
        int availableMaximumScore = 0;

        // 1. Studiengang
        if (hasText(currentUser.getDegreeProgram())
                && hasText(candidate.getDegreeProgram())) {

            availableMaximumScore += weights.degreeProgram();

            if (equalsIgnoreCase(
                    currentUser.getDegreeProgram(),
                    candidate.getDegreeProgram())) {

                score += weights.degreeProgram();
            }
        }

        // 2. Hochschule
        if (hasUniversity(currentUser)
                && hasUniversity(candidate)) {

            availableMaximumScore += weights.university();

            if (sameUniversity(currentUser, candidate)) {
                score += weights.university();
            }
        }

        // 3. Stadt
        if (hasText(currentUser.getCity())
                && hasText(candidate.getCity())) {

            availableMaximumScore += weights.city();

            if (equalsIgnoreCase(
                    currentUser.getCity(),
                    candidate.getCity())) {

                score += weights.city();
            }
        }

        // 4. Semester
        if (currentUser.getSemester() != null
                && candidate.getSemester() != null) {

            availableMaximumScore += weights.semester();

            if (isSimilarSemester(currentUser, candidate)) {
                score += weights.semester();
            }
        }

        // 5. Verfügbare Lernzeit
        if (hasText(currentUser.getAvailableTime())
                && hasText(candidate.getAvailableTime())) {

            availableMaximumScore += weights.availableTime();

            if (equalsIgnoreCase(
                    currentUser.getAvailableTime(),
                    candidate.getAvailableTime())) {

                score += weights.availableTime();
            }
        }

        // 6. Lernform
        if (currentUser.getStudyMode() != null
                && candidate.getStudyMode() != null) {

            availableMaximumScore += weights.studyMode();

            if (currentUser.getStudyMode() == candidate.getStudyMode()) {
                score += weights.studyMode();
            }
        }

        // 7. Sprache
        if (hasText(currentUser.getLanguage())
                && hasText(candidate.getLanguage())) {

            availableMaximumScore += weights.language();

            if (equalsIgnoreCase(
                    currentUser.getLanguage(),
                    candidate.getLanguage())) {

                score += weights.language();
            }
        }

        // 8. Lernstil
        if (currentUser.getLearningStyle() != null
                && candidate.getLearningStyle() != null) {

            availableMaximumScore += weights.learningStyle();

            if (currentUser.getLearningStyle()
                    == candidate.getLearningStyle()) {

                score += weights.learningStyle();
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

    private Weights selectWeights(User currentUser) {

        StudyMode studyMode = currentUser.getStudyMode();

        // Kein expliziter Kontext:
        // Rückgriff auf die ursprünglichen Gewichte.
        if (studyMode == null) {
            return new Weights(
                    30, // Studiengang
                    15, // Hochschule
                    10, // Stadt
                    15, // Semester
                    20, // Lernzeit
                    10, // Lernform
                    10, // Sprache
                    10  // Lernstil
            );
        }

        return switch (studyMode) {

            case ONLINE -> new Weights(
                    24, // Studiengang
                    6,  // Hochschule
                    6,  // Stadt
                    12, // Semester
                    24, // Lernzeit
                    24, // Lernform
                    12, // Sprache
                    12  // Lernstil
            );

            case OFFLINE -> new Weights(
                    18, // Studiengang
                    18, // Hochschule
                    18, // Stadt
                    10, // Semester
                    18, // Lernzeit
                    18, // Lernform
                    10, // Sprache
                    10  // Lernstil
            );

            case BOTH -> new Weights(
                    20, // Studiengang
                    12, // Hochschule
                    12, // Stadt
                    12, // Semester
                    20, // Lernzeit
                    20, // Lernform
                    12, // Sprache
                    12  // Lernstil
            );
        };
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

    private record Weights(
            int degreeProgram,
            int university,
            int city,
            int semester,
            int availableTime,
            int studyMode,
            int language,
            int learningStyle
    ) {
    }
}