//  username 기반으로 리팩토링된 ChatThreadController

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
    public ResponseEntity<ChatThreadResponseDto> createThread(
            @RequestHeader("X-Auth-Username") String username,
            @RequestBody ChatThreadDto dto
    ) {
        dto.setUsername(username);
        ChatThreadEntity created = chatThreadService.createThread(dto);
        return ResponseEntity.ok(ChatThreadResponseDto.toDto(created));
    }

    // 소유자 검증 추가
    @GetMapping("/{threadId}")
    public ResponseEntity<ChatThreadResponseDto> getThreadById(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long threadId
    ) {
        ChatThreadEntity thread = chatThreadService.getThreadById(threadId, username);
        return ResponseEntity.ok(ChatThreadResponseDto.toDto(thread));
    }


    @GetMapping("/me")
    public ResponseEntity<List<ChatThreadResponseDto>> getThreadsByUsername(
            @RequestHeader("X-Auth-Username") String username
    ) {
        List<ChatThreadResponseDto> response = chatThreadService.getThreadsByUsername(username).stream()
                .map(ChatThreadResponseDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<Void> deleteThread(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long threadId
    ) {
        chatThreadService.deleteThread(username, threadId);
        return ResponseEntity.noContent().build();
    }
}
