package com.example.backend.matching;

import com.example.backend.entity.University;
import com.example.backend.entity.User;
import com.example.backend.enums.LearningStyle;
import com.example.backend.enums.StudyMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NormalizedMatchingStrategyTest {

    private final NormalizedMatchingStrategy strategy =
            new NormalizedMatchingStrategy();


    // S1: Vollständige Profile
    @Test
    void s1_completeProfilesShouldProduceExpectedScoresAndOrder() {

        User reference = createCompleteUser(StudyMode.ONLINE);

        MatchResult candidateA =
                strategy.calculate(reference, createCandidateA(StudyMode.ONLINE));

        MatchResult candidateB =
                strategy.calculate(reference, createCandidateB(StudyMode.ONLINE));

        MatchResult candidateC =
                strategy.calculate(reference, createCandidateC());

        assertEquals(120, candidateA.rawScore());
        assertEquals(120, candidateA.maximumScore());
        assertEquals(100, candidateA.percentage());
        assertEquals(100.0, candidateA.informationCoverage(), 0.01);

        assertEquals(95, candidateB.rawScore());
        assertEquals(120, candidateB.maximumScore());
        assertEquals(79, candidateB.percentage());
        assertEquals(100.0, candidateB.informationCoverage(), 0.01);

        assertEquals(50, candidateC.rawScore());
        assertEquals(120, candidateC.maximumScore());
        assertEquals(41, candidateC.percentage());
        assertEquals(100.0, candidateC.informationCoverage(), 0.01);

        assertTrue(candidateA.percentage() > candidateB.percentage());
        assertTrue(candidateB.percentage() > candidateC.percentage());
    }


    // S2
    @Test
    void s2_incompleteCandidateAShouldReturn100PercentWithReducedCoverage() {

        User reference = createCompleteUser(StudyMode.ONLINE);
        User candidateA = createCandidateA(StudyMode.ONLINE);

        candidateA.setUniversity(null);
        candidateA.setCity(null);
        candidateA.setAvailableTime(null);

        MatchResult result = strategy.calculate(reference, candidateA);

        assertEquals(75, result.rawScore());
        assertEquals(75, result.maximumScore());
        assertEquals(100, result.percentage());
        assertEquals(62.5, result.informationCoverage(), 0.01);
    }


    @Test
    void s2_missingUniversityShouldReturn100PercentWith87Point5Coverage() {

        User reference = createCompleteUser(StudyMode.ONLINE);
        User candidateA = createCandidateA(StudyMode.ONLINE);

        candidateA.setUniversity(null);

        MatchResult result = strategy.calculate(reference, candidateA);

        assertEquals(105, result.rawScore());
        assertEquals(105, result.maximumScore());
        assertEquals(100, result.percentage());
        assertEquals(87.5, result.informationCoverage(), 0.01);
    }


    @Test
    void s2_missingCityShouldReturn100PercentWith91Point67Coverage() {

        User reference = createCompleteUser(StudyMode.ONLINE);
        User candidateA = createCandidateA(StudyMode.ONLINE);

        candidateA.setCity(null);

        MatchResult result = strategy.calculate(reference, candidateA);

        assertEquals(110, result.rawScore());
        assertEquals(110, result.maximumScore());
        assertEquals(100, result.percentage());
        assertEquals(91.67, result.informationCoverage(), 0.01);
    }


    @Test
    void s2_missingAvailableTimeShouldReturn100PercentWith83Point33Coverage() {

        User reference = createCompleteUser(StudyMode.ONLINE);
        User candidateA = createCandidateA(StudyMode.ONLINE);

        candidateA.setAvailableTime(null);

        MatchResult result = strategy.calculate(reference, candidateA);

        assertEquals(100, result.rawScore());
        assertEquals(100, result.maximumScore());
        assertEquals(100, result.percentage());
        assertEquals(83.33, result.informationCoverage(), 0.01);
    }


    // S3
    @Test
    void s3_candidateBShouldRemain79PercentInAllContexts() {

        MatchResult online = strategy.calculate(
                createCompleteUser(StudyMode.ONLINE),
                createCandidateB(StudyMode.ONLINE)
        );

        MatchResult offline = strategy.calculate(
                createCompleteUser(StudyMode.OFFLINE),
                createCandidateB(StudyMode.OFFLINE)
        );

        MatchResult hybrid = strategy.calculate(
                createCompleteUser(StudyMode.BOTH),
                createCandidateB(StudyMode.BOTH)
        );

        assertEquals(95, online.rawScore());
        assertEquals(120, online.maximumScore());
        assertEquals(79, online.percentage());

        assertEquals(95, offline.rawScore());
        assertEquals(120, offline.maximumScore());
        assertEquals(79, offline.percentage());

        assertEquals(95, hybrid.rawScore());
        assertEquals(120, hybrid.maximumScore());
        assertEquals(79, hybrid.percentage());

        assertEquals(100.0, online.informationCoverage(), 0.01);
        assertEquals(100.0, offline.informationCoverage(), 0.01);
        assertEquals(100.0, hybrid.informationCoverage(), 0.01);
    }


    // S4
    @Test
    void s4_sparseCandidateDShouldHaveHigherPercentageThanCompleteCandidateC() {

        User reference = createCompleteUser(StudyMode.ONLINE);

        MatchResult candidateC =
                strategy.calculate(reference, createCandidateC());

        MatchResult candidateD =
                strategy.calculate(reference, createCandidateD());

        assertEquals(50, candidateC.rawScore());
        assertEquals(120, candidateC.maximumScore());
        assertEquals(41, candidateC.percentage());
        assertEquals(100.0, candidateC.informationCoverage(), 0.01);

        assertEquals(20, candidateD.rawScore());
        assertEquals(20, candidateD.maximumScore());
        assertEquals(100, candidateD.percentage());
        assertEquals(16.67, candidateD.informationCoverage(), 0.01);

        assertTrue(candidateD.percentage() > candidateC.percentage());
    }


    @Test
    void technical_emptyProfilesShouldReturnZero() {

        User first = new User();
        User second = new User();

        MatchResult result = strategy.calculate(first, second);

        assertEquals(0, result.rawScore());
        assertEquals(0, result.maximumScore());
        assertEquals(0, result.percentage());
        assertEquals(0.0, result.informationCoverage(), 0.01);
    }


    private User createCandidateA(StudyMode mode) {

        User user = createCompleteUser(mode);
        user.setSemester(5);

        return user;
    }


    private User createCandidateB(StudyMode mode) {

        User user = createCompleteUser(mode);

        University university = new University();
        university.setName("Hochschule Bochum");

        user.setUniversity(university);
        user.setCity("Bochum");

        return user;
    }


    private User createCandidateC() {

        User user = createCompleteUser(StudyMode.ONLINE);

        user.setDegreeProgram("Wirtschaftsinformatik");
        user.setAvailableTime("Morgens");
        user.setStudyMode(StudyMode.BOTH);
        user.setLanguage("Englisch");

        return user;
    }


    private User createCandidateD() {

        User user = new User();

        user.setLanguage("Deutsch");
        user.setLearningStyle(LearningStyle.GROUP);

        return user;
    }


    private User createCompleteUser(StudyMode mode) {

        University university = new University();
        university.setName("FH Dortmund");

        User user = new User();

        user.setDegreeProgram("Informatik");
        user.setUniversity(university);
        user.setCity("Dortmund");
        user.setSemester(4);
        user.setAvailableTime("Abends");
        user.setStudyMode(mode);
        user.setLanguage("Deutsch");
        user.setLearningStyle(LearningStyle.GROUP);

        return user;
    }
}