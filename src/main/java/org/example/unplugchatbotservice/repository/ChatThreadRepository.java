package org.example.unplugchatbotservice.repository;


import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ChatThreadRepository extends JpaRepository<ChatThreadEntity, Long> {
    List<ChatThreadEntity> findByUserId(Long userId);
}