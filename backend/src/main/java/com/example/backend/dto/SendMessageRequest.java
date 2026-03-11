package com.example.backend.dto;

import lombok.Data;

@Data
public class SendMessageRequest {
    private Long conversationId;
    private Long senderId;
    private String content;
}