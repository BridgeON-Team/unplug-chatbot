//  username 기반으로 리팩토링된 ChatThreadService

package org.example.unplugchatbotservice.service;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.example.unplugchatbotservice.dto.ChatThreadDto;
import org.example.unplugchatbotservice.repository.ChatThreadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatThreadService {
    private final ChatThreadRepository chatThreadRepository;

    public ChatThreadEntity createThread(ChatThreadDto dto) {
        ChatThreadEntity entity = ChatThreadEntity.toEntity(dto);
        return chatThreadRepository.save(entity);
    }

    public ChatThreadEntity getThreadById(long threadId, String username) {
        ChatThreadEntity thread = chatThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));

        if (!Objects.equals(thread.getUsername(), username)) {
            throw new SecurityException("You are not authorized to access this thread.");
        }
        return thread;
    }


    public List<ChatThreadEntity> getThreadsByUsername(String username) {
        return chatThreadRepository.findByUsername(username);
    }

    public void deleteThread(String username, long threadId) {
        validateThreadOwnership(threadId, username);
        chatThreadRepository.deleteById(threadId);
    }

    // username 기준으로 소유자 검증
    public void validateThreadOwnership(Long threadId, String username) {
        ChatThreadEntity thread = chatThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));

        if (!thread.getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to access this thread.");
        }
    }
}

