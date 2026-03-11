package com.example.backend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageResponse {
    private Long id;
    private Long conversationId;
    private Long senderId;
    private String senderName;
    private String content;
    private Instant createdAt;
}