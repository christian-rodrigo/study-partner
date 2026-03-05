package com.example.backend.repository;

import com.example.backend.entity.Swipe;
import com.example.backend.entity.SwipeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    Optional<Swipe> findBySwiper_IdAndSwiped_Id(Long swiperId, Long swipedId);
    boolean existsBySwiper_IdAndSwiped_IdAndType(Long swiperId, Long swipedId, SwipeType type);
}