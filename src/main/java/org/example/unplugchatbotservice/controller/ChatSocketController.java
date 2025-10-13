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

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final ChatMessageService chatMessageService;
    private final ChatThreadService chatThreadService;

    @MessageMapping("/threads/{threadId}/send")
    @SendTo("/topic/threads/{threadId}")
    public ChatMessageDto sendMessage(@DestinationVariable Long threadId,
                                      ChatMessageDto dto,
                                      Principal principal) {

        // 인증된 사용자명 사용
        String username = principal.getName();

        // 1. 보낸 사람 검증 (dto가 아닌, 인증된 사용자 기반으로)
        chatThreadService.validateThreadOwnership(threadId, username);

        // 2. threadId와 username 명시적으로 지정
        dto.setThreadId(threadId);
        dto.setUsername(username);

        // 3. 메시지 저장 및 bot 응답 처리
        ChatMessageEntity saved = chatMessageService.sendMessage(dto);
        return ChatMessageDto.toDto(saved);
    }

}
