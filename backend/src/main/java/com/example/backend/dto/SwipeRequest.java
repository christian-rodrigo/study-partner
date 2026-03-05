package com.example.backend.dto;

import com.example.backend.entity.SwipeType;
import lombok.Data;

@Data
public class SwipeRequest {
    private Long swiperId;
    private Long swipedId;
    private SwipeType type; // LIKE / DISLIKE
}