package com.example.backend.matching;

import org.springframework.stereotype.Component;

@Component
public class MatchingStrategyResolver {

    private final WeightedMatchingStrategy weightedMatchingStrategy;
    private final NormalizedMatchingStrategy normalizedMatchingStrategy;
    private final ContextSensitiveMatchingStrategy contextSensitiveMatchingStrategy;

    public MatchingStrategyResolver(
            WeightedMatchingStrategy weightedMatchingStrategy,
            NormalizedMatchingStrategy normalizedMatchingStrategy,
            ContextSensitiveMatchingStrategy contextSensitiveMatchingStrategy) {

        this.weightedMatchingStrategy = weightedMatchingStrategy;
        this.normalizedMatchingStrategy = normalizedMatchingStrategy;
        this.contextSensitiveMatchingStrategy = contextSensitiveMatchingStrategy;
    }

    public MatchingStrategy resolve(MatchingStrategyType type) {

        return switch (type) {
            case WEIGHTED -> weightedMatchingStrategy;
            case NORMALIZED -> normalizedMatchingStrategy;
            case CONTEXTUAL -> contextSensitiveMatchingStrategy;
        };
    }
}