// ✅ username 기반으로 리팩토링된 ChatMessageDto

package org.example.unplugchatbotservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.example.unplugchatbotservice.domain.SenderStatus;

@Getter
@Setter
public class ChatMessageDto {

    private Long chatMessageId;
    private Long threadId;
    private String username; // userId → username으로 변경
    private SenderStatus sender;
    private String message;

    // Entity → DTO 변환
    public static ChatMessageDto toDto(ChatMessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setChatMessageId(entity.getChatMessageId());
        dto.setThreadId(entity.getChatThread().getThreadId());
        dto.setUsername(entity.getUsername());
        dto.setSender(entity.getSender());
        dto.setMessage(entity.getMessage());
        return dto;
    }
}