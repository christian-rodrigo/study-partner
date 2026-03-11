package com.example.backend.dto;

import lombok.Data;

@Data
public class OpenConversationRequest {
    private Long user1Id;
    private Long user2Id;
}