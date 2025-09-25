package org.example.unplugchatbotservice.service;


import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.example.unplugchatbotservice.dto.ChatThreadDto;
import org.example.unplugchatbotservice.repository.ChatThreadRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatThreadService {
    private final ChatThreadRepository chatThreadRepository;


    public ChatThreadEntity createThread(ChatThreadDto dto) {
        ChatThreadEntity entity = ChatThreadEntity.toEntity(dto);
        return chatThreadRepository.save(entity);
    }


    public ChatThreadEntity getThreadById(long threadId) {
        return chatThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));
    }


    public List<ChatThreadEntity> getThreadsByUser(long userId) {
        return chatThreadRepository.findByUserId(userId);
    }


    public void deleteThread(long threadId) {
        ChatThreadEntity thread = chatThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));
        chatThreadRepository.delete(thread);
    }
}