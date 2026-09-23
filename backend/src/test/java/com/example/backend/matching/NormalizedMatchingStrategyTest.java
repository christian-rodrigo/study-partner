package com.example.backend.matching;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalizedMatchingStrategyTest {

    private final NormalizedMatchingStrategy strategy =
            new NormalizedMatchingStrategy();

    @Test
    void identicalCompleteProfilesShouldReturn100Percent() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        MatchResult result = strategy.calculate(first, second);

        assertEquals(120, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(100, result.percentage());
    }

    @Test
    void identicalPartialProfilesShouldReturn100Percent() {

        User first = new User();
        first.setDegreeProgram("Informatik");
        first.setAvailableTime("Abends");
        first.setLanguage("Deutsch");

        User second = new User();
        second.setDegreeProgram("Informatik");
        second.setAvailableTime("Abends");
        second.setLanguage("Deutsch");

        MatchResult result = strategy.calculate(first, second);

        assertEquals(60, result.rawScore());
        assertEquals(60, result.maximumScore());
        assertEquals(100, result.percentage());
    }

    @Test
    void mismatchShouldReducePercentageOnlyWithinAvailableCriteria() {

        User first = new User();
        first.setDegreeProgram("Informatik");
        first.setAvailableTime("Abends");
        first.setLanguage("Deutsch");

        User second = new User();
        second.setDegreeProgram("Informatik");
        second.setAvailableTime("Morgens");
        second.setLanguage("Deutsch");

        MatchResult result = strategy.calculate(first, second);

        assertEquals(40, result.rawScore());
        assertEquals(60, result.maximumScore());
        assertEquals(66, result.percentage());
    }

    @Test
    void completelyEmptyProfilesShouldReturnZero() {

        User first = new User();
        User second = new User();

        MatchResult result = strategy.calculate(first, second);

        assertEquals(0, result.rawScore());
        assertEquals(0, result.maximumScore());
        assertEquals(0, result.percentage());
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