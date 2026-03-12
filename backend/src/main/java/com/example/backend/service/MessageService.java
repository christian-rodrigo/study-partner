package com.example.backend.service;

import com.example.backend.dto.MessageResponse;
import com.example.backend.entity.Conversation;
import com.example.backend.entity.Message;
import com.example.backend.entity.User;
import com.example.backend.repository.ConversationRepository;
import com.example.backend.repository.MessageRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository,
                          ConversationRepository conversationRepository,
                          UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public MessageResponse sendMessage(Long conversationId, Long senderId, String content) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        boolean isParticipant =
                conversation.getUser1().getId().equals(senderId) ||
                        conversation.getUser2().getId().equals(senderId);

        if (!isParticipant) {
            throw new RuntimeException("Sender is not part of this conversation");
        }

        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);

        Message saved = messageRepository.save(message);

        return mapToResponse(saved);
    }

    public List<MessageResponse> getMessagesByConversation(Long conversationId) {
        return messageRepository.findByConversation_IdOrderByCreatedAtAsc(conversationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private MessageResponse mapToResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setConversationId(message.getConversation().getId());
        response.setSenderId(message.getSender().getId());
        response.setSenderName(message.getSender().getName());
        response.setContent(message.getContent());
        response.setCreatedAt(message.getCreatedAt());
        return response;
    }
}
