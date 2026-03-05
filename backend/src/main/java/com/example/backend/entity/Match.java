package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "matches",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // keep ordering consistent (smaller id -> user1)
    @ManyToOne(optional = false)
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user2_id")
    private User user2;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}