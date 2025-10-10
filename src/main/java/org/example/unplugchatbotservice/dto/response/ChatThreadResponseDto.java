// ✅ username 기반으로 리팩토링된 ChatThreadResponseDto

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
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static ChatThreadResponseDto toDto(ChatThreadEntity entity){
        ChatThreadResponseDto dto = new ChatThreadResponseDto();
        dto.threadId = entity.getThreadId();
        dto.title = entity.getTitle();
        dto.username = entity.getUsername();
        dto.createdDate = entity.getCreatedDate();
        dto.updatedDate = entity.getUpdateDate();
        return dto;
    }
}