package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class Swipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long swiperId;

    private Long swipedUserId;

    private String type; // LIKE or DISLIKE
}