//  username 기반으로 리팩토링된 ChatMessageController

package org.example.unplugchatbotservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.service.ChatMessageService;
import org.example.unplugchatbotservice.service.ChatThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatbot/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ChatThreadService chatThreadService;

    // 메시지 전송 (인증 사용자 전용)
    @Operation(summary = "메시지 전송", description = "현재 사용자(username 기준)가 특정 스레드에 메시지를 전송. GPT 응답은 자동으로 추가.")
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

    @Operation(summary = "스레드별 메시지 조회", description = "해당 스레드의 모든 메시지를 시간순으로 반환. 자신의 스레드만 접근 가능.")
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

    @Operation(summary = "메시지 삭제", description = "자신이 작성한 메시지만 삭제가능.")
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long messageId
    ) {
        chatMessageService.deleteMessage(username, messageId);
        return ResponseEntity.noContent().build();
    }

}