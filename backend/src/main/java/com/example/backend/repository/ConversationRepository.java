package com.example.backend.repository;

import com.example.backend.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByUser1_IdAndUser2_Id(Long user1Id, Long user2Id);

    List<Conversation> findByUser1_IdOrUser2_Id(Long user1Id, Long user2Id);
}