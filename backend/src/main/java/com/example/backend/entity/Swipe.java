package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "swipes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"swiper_id", "swiped_id"}))
public class Swipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who swiped
    @ManyToOne(optional = false)
    @JoinColumn(name = "swiper_id")
    private User swiper;

    // who got swiped
    @ManyToOne(optional = false)
    @JoinColumn(name = "swiped_id")
    private User swiped;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SwipeType type;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}