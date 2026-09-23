package com.example.backend.controller;

import com.example.backend.dto.MessageResponse;
import com.example.backend.dto.SendMessageRequest;
import com.example.backend.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(
            MessageService messageService,
            SimpMessagingTemplate messagingTemplate) {

        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(SendMessageRequest request) {

        MessageResponse savedMessage = messageService.sendMessage(
                request.getConversationId(),
                request.getSenderId(),
                request.getContent()
        );

        messagingTemplate.convertAndSend(
                "/topic/conversation/" + savedMessage.getConversationId(),
                savedMessage
        );
    }
}