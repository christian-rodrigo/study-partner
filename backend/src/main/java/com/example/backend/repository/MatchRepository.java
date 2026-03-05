package com.example.backend.repository;

import com.example.backend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByUser1_IdAndUser2_Id(Long user1Id, Long user2Id);
}