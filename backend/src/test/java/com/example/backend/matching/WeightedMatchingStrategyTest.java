package com.example.backend.matching;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedMatchingStrategyTest {

    private final WeightedMatchingStrategy strategy =
            new WeightedMatchingStrategy();

    @Test
    void identicalProfilesShouldReturn100Percent() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        MatchResult result = strategy.calculate(first, second);

        assertEquals(120, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(100, result.percentage());
    }
    @Test
    void semesterDifferenceOfOneShouldStillMatch() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        first.setSemester(4);
        second.setSemester(5);

        MatchResult result = strategy.calculate(first, second);

        assertEquals(120, result.rawScore());
        assertEquals(100, result.percentage());
    }
    @Test
    void missingProfileDataShouldNotProducePoints() {

        User first = new User();
        User second = new User();

        MatchResult result = strategy.calculate(first, second);

        assertEquals(0, result.rawScore());
        assertEquals(120, result.maximumScore());
        assertEquals(0, result.percentage());
    }
    @Test
    void semesterDifferenceGreaterThanOneShouldNotMatch() {

        User first = createCompleteUser();
        User second = createCompleteUser();

        first.setSemester(4);
        second.setSemester(6);

        MatchResult result = strategy.calculate(first, second);

        assertEquals(105, result.rawScore());
        assertEquals(87, result.percentage());
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