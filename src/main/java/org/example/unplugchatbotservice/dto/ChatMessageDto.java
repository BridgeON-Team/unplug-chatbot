package org.example.unplugchatbotservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.example.unplugchatbotservice.domain.SenderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {

    private Long chatMessageId;
    private Long threadId;
    private Long userId;
    private SenderStatus sender;
    private String message;

    // ✅ Entity → DTO 변환
    public static ChatMessageDto toDto(ChatMessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(entity.getChatMessageId());
        dto.setThreadId(entity.getChatThread().getThreadId());
        dto.setUserId(entity.getUserId());
        dto.setSender(entity.getSender());
        dto.setMessage(entity.getMessage());
        return dto;
    }
}