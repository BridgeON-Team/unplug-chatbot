package org.example.unplugchatbotservice.repository;

import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    List<ChatMessageEntity> findByChatThread_ThreadIdOrderByCreatedAtAsc(Long threadId);
}
