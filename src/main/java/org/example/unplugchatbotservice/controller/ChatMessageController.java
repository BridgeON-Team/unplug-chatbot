//  username 기반으로 리팩토링된 ChatMessageController

package org.example.unplugchatbotservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.service.ChatMessageService;
import org.example.unplugchatbotservice.service.ChatThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ChatThreadService chatThreadService;

    // 메시지 전송 (인증 사용자 전용)
    @PostMapping
    public ResponseEntity<ChatMessageDto> sendMessage(
            @RequestHeader("X-Auth-Username") String username,
            @RequestBody ChatMessageDto dto
    ) {
        dto.setUsername(username);
        // 소유자 확인
        chatThreadService.validateThreadOwnership(dto.getThreadId(), username);

        // 메시지 전송
        ChatMessageDto response = ChatMessageDto.toDto(
                chatMessageService.sendMessage(dto)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/thread/{threadId}")
    public ResponseEntity<List<ChatMessageDto>> getMessagesByThread(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long threadId
    ) {
        // 소유자 검증
        chatThreadService.validateThreadOwnership(threadId, username);

        // username 기반으로 메시지 조회
        List<ChatMessageDto> response = chatMessageService.getMessagesByThread(threadId, username)
                .stream()
                .map(ChatMessageDto::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long messageId
    ) {
        chatMessageService.deleteMessage(username, messageId);
        return ResponseEntity.noContent().build();
    }

}