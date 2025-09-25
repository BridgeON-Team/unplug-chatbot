package org.example.unplugchatbotservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/threads/{threadId}/send")
    @SendTo("/topic/threads/{threadId}")
    public ChatMessageDto sendMessage(@DestinationVariable Long threadId, ChatMessageDto dto) {
        dto.setThreadId(threadId);
        ChatMessageEntity entity = chatMessageService.sendMessage(dto);
        return ChatMessageDto.toDto(entity); // ✅ Dto로 변환해서 반환
    }
}
