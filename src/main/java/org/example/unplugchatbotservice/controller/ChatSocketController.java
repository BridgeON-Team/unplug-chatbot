package org.example.unplugchatbotservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.service.ChatMessageService;
import org.example.unplugchatbotservice.service.ChatThreadService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final ChatMessageService chatMessageService;
    private final ChatThreadService chatThreadService;

    @MessageMapping("/threads/{threadId}/send")
    @SendTo("/topic/threads/{threadId}")
    public ChatMessageDto sendMessage(@DestinationVariable Long threadId, ChatMessageDto dto) {

        // 1. 클라이언트가 잘못된 threadId + username 조합을 보내는 것을 방지
        chatThreadService.validateThreadOwnership(threadId, dto.getUsername());

        // 2. threadId 설정 (URL과 dto 분리 방지)
        dto.setThreadId(threadId);

        // 3. 메시지 저장 및 bot 응답 처리
        return ChatMessageDto.toDto(chatMessageService.sendMessage(dto));
    }
}
