package org.example.unplugchatbotservice.repository;

import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    List<ChatMessageEntity> findByChatThreadThreadIdAndUsernameOrderByCreatedAtAsc(Long threadId, String username);
    Optional<ChatMessageEntity> findByChatMessageIdAndUsername(Long messageId, String username);
}
