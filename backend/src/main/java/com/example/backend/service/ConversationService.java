package com.example.backend.service;

import com.example.backend.dto.ConversationResponse;
import com.example.backend.entity.Conversation;
import com.example.backend.entity.User;
import com.example.backend.repository.ConversationRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository,
                               UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public ConversationResponse openConversation(Long user1Id, Long user2Id) {
        if (user1Id.equals(user2Id)) {
            throw new RuntimeException("Users cannot open a conversation with themselves");
        }

        Long smallerId = Math.min(user1Id, user2Id);
        Long biggerId = Math.max(user1Id, user2Id);

        Conversation conversation = conversationRepository
                .findByUser1_IdAndUser2_Id(smallerId, biggerId)
                .orElseGet(() -> {
                    User user1 = userRepository.findById(smallerId).orElseThrow();
                    User user2 = userRepository.findById(biggerId).orElseThrow();

                    Conversation newConversation = new Conversation();
                    newConversation.setUser1(user1);
                    newConversation.setUser2(user2);

                    return conversationRepository.save(newConversation);
                });

        return mapToResponse(conversation);
    }

    public List<ConversationResponse> getUserConversations(Long userId) {
        return conversationRepository.findByUser1_IdOrUser2_Id(userId, userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ConversationResponse mapToResponse(Conversation conversation) {
        ConversationResponse response = new ConversationResponse();
        response.setId(conversation.getId());
        response.setUser1Id(conversation.getUser1().getId());
        response.setUser1Name(conversation.getUser1().getName());
        response.setUser2Id(conversation.getUser2().getId());
        response.setUser2Name(conversation.getUser2().getName());
        response.setCreatedAt(conversation.getCreatedAt());
        return response;
    }
}