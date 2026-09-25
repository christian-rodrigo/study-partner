package com.example.backend.matching;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContextSensitiveMatchingStrategyTest {

    private final ContextSensitiveMatchingStrategy strategy =
            new ContextSensitiveMatchingStrategy();

    @Test
    void identicalOnlineProfilesShouldReturn100Percent() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        first.setStudyMode(StudyMode.ONLINE);
        second.setStudyMode(StudyMode.ONLINE);

        MatchResult result = strategy.calculate(first, second);

        assertEquals(120, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(100, result.percentage());
    }

    @Test
    void differentCityAndUniversityShouldMatterLessOnline() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        first.setStudyMode(StudyMode.ONLINE);
        second.setStudyMode(StudyMode.ONLINE);

        University otherUniversity = new University();
        otherUniversity.setName("TU Dortmund");

        second.setUniversity(otherUniversity);
        second.setCity("Bochum");

        MatchResult result = strategy.calculate(first, second);

        assertEquals(108, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(90, result.percentage());
    }

    @Test
    void differentCityAndUniversityShouldMatterMoreOffline() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        first.setStudyMode(StudyMode.OFFLINE);
        second.setStudyMode(StudyMode.OFFLINE);

        University otherUniversity = new University();
        otherUniversity.setName("TU Dortmund");

        second.setUniversity(otherUniversity);
        second.setCity("Bochum");

        MatchResult result = strategy.calculate(first, second);

        assertEquals(84, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(70, result.percentage());
    }

    @Test
    void missingDataShouldNormalizeAvailableCriteria() {

        User first = new User();
        User second = new User();

        first.setStudyMode(StudyMode.ONLINE);
        second.setStudyMode(StudyMode.ONLINE);

        first.setDegreeProgram("Informatik");
        second.setDegreeProgram("Informatik");

        first.setLanguage("Deutsch");
        second.setLanguage("Deutsch");

        MatchResult result = strategy.calculate(first, second);

        assertEquals(60, result.rawScore());
        assertEquals(60, result.maximumScore());
        assertEquals(100, result.percentage());
    }

    private User createCompleteUser() {

        University university = new University();
        university.setName("FH Dortmund");

        User user = new User();
        user.setDegreeProgram("Informatik");
        user.setUniversity(university);
        user.setCity("Dortmund");
        user.setSemester(4);
        user.setAvailableTime("Abends");
        user.setStudyMode(StudyMode.BOTH);
        user.setLanguage("Deutsch");
        user.setLearningStyle(LearningStyle.GROUP);

        return user;
    }
}