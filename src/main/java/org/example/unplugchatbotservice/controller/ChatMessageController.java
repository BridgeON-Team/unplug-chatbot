package org.example.unplugchatbotservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.service.ChatMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    // ✅ 메시지 전송
    @PostMapping
    public ResponseEntity<ChatMessageDto> sendMessage(@RequestBody ChatMessageDto dto) {
        return ResponseEntity.ok(
                ChatMessageDto.toDto(chatMessageService.sendMessage(dto))
        );
    }

    // ✅ 스레드 ID로 메시지 조회
    @GetMapping("/thread/{threadId}")
    public ResponseEntity<List<ChatMessageDto>> getMessagesByThread(@PathVariable Long threadId) {
        return ResponseEntity.ok(
                chatMessageService.getMessagesByThread(threadId).stream()
                        .map(ChatMessageDto::toDto)
                        .collect(Collectors.toList())
        );
    }

    // ✅ 메시지 삭제
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        chatMessageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
