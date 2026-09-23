package com.example.backend.matching;

import com.example.backend.entity.User;

public interface MatchingStrategy {

    MatchResult calculate(User currentUser, User candidate);
}