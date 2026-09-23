package com.example.backend.matching;

public record MatchResult(
        int rawScore,
        int maximumScore,
        int percentage
) {
}