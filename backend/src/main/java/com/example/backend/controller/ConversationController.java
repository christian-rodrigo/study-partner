package com.example.backend.controller;

import com.example.backend.dto.ConversationResponse;
import com.example.backend.dto.OpenConversationRequest;
import com.example.backend.service.ConversationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/open")
    public ConversationResponse openConversation(@RequestBody OpenConversationRequest request) {
        return conversationService.openConversation(request.getUser1Id(), request.getUser2Id());
    }

    @GetMapping("/user/{userId}")
    public List<ConversationResponse> getUserConversations(@PathVariable Long userId) {
        return conversationService.getUserConversations(userId);
    }
}