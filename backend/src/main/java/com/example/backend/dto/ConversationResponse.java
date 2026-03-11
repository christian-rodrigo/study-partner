package com.example.backend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ConversationResponse {
    private Long id;
    private Long user1Id;
    private String user1Name;
    private Long user2Id;
    private String user2Name;
    private Instant createdAt;
}