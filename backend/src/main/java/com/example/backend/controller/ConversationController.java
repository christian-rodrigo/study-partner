package com.example.backend.controller;

import com.example.backend.dto.ConversationResponse;
import com.example.backend.dto.OpenConversationRequest;
import com.example.backend.service.ConversationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;
    private final SimpMessagingTemplate messagingTemplate;

    public ConversationController(
            ConversationService conversationService,
            SimpMessagingTemplate messagingTemplate) {

        this.conversationService = conversationService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/open")
    public ConversationResponse openConversation(
            @RequestBody OpenConversationRequest request) {

        ConversationResponse conversation =
                conversationService.openConversation(
                        request.getUser1Id(),
                        request.getUser2Id()
                );

        messagingTemplate.convertAndSend(
                "/topic/user/" + conversation.getUser1Id() + "/conversations",
                conversation
        );

        messagingTemplate.convertAndSend(
                "/topic/user/" + conversation.getUser2Id() + "/conversations",
                conversation
        );

        return conversation;
    }

    @GetMapping("/user/{userId}")
    public List<ConversationResponse> getUserConversations(@PathVariable Long userId) {
        return conversationService.getUserConversations(userId);
    }
}