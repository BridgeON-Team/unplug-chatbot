package org.example.unplugchatbotservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.unplugchatbotservice.domain.ChatThreadEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatThreadResponseDto {
    private Long threadId;
    private String title;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static ChatThreadResponseDto toDto(ChatThreadEntity entity){
        ChatThreadResponseDto dto = new ChatThreadResponseDto();
        dto.threadId = entity.getThreadId();
        dto.title = entity.getTitle();
        dto.userId = entity.getUserId();
        dto.createdDate = entity.getCreatedDate();
        dto.updatedDate = entity.getUpdateDate();
        return dto;
    }
}
