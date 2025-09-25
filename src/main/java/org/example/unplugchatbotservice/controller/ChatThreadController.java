package org.example.unplugchatbotservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.example.unplugchatbotservice.dto.ChatThreadDto;
import org.example.unplugchatbotservice.dto.response.ChatThreadResponseDto;
import org.example.unplugchatbotservice.service.ChatThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
public class ChatThreadController {
    private final ChatThreadService chatThreadService;

    @PostMapping
    public ResponseEntity<ChatThreadResponseDto> createThread(@RequestBody ChatThreadDto dto) {
        ChatThreadEntity created = chatThreadService.createThread(dto);
        return ResponseEntity.ok(ChatThreadResponseDto.toDto(created));
    }

    @GetMapping("/{threadId}")
    public ResponseEntity<ChatThreadResponseDto> getThreadById(@PathVariable Long threadId) {
        ChatThreadEntity thread = chatThreadService.getThreadById(threadId);
        return ResponseEntity.ok(ChatThreadResponseDto.toDto(thread));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatThreadResponseDto>> getThreadByUser(@PathVariable Long userId) {
        List<ChatThreadEntity> threads = chatThreadService.getThreadsByUser(userId);
        List<ChatThreadResponseDto> response = threads.stream().map(ChatThreadResponseDto::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{threadId}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long threadId) {
        chatThreadService.deleteThread(threadId);
        return ResponseEntity.noContent().build();
    }

}