//  username 기반으로 리팩토링된 ChatThreadController

package org.example.unplugchatbotservice.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "채팅 스레드 생성", description = "새로운 채팅 스레드를 생성합니다. 사용자명은 Gateway에서 전달된 X-Auth-Username 헤더로 식별됩니다.")
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
    @Operation(summary = "단일 스레드 조회", description = "스레드 ID를 기준으로 자신의 채팅 스레드를 조회합니다. 다른 사용자의 스레드 접근 시 SecurityException이 발생합니다.")
    @GetMapping("/{threadId}")
    public ResponseEntity<ChatThreadResponseDto> getThreadById(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long threadId
    ) {
        ChatThreadEntity thread = chatThreadService.getThreadById(threadId, username);
        return ResponseEntity.ok(ChatThreadResponseDto.toDto(thread));
    }

    @Operation(summary = "내 스레드 목록 조회", description = "로그인된 사용자의 모든 채팅 스레드 목록을 반환합니다.")
    @GetMapping("/me")
    public ResponseEntity<List<ChatThreadResponseDto>> getThreadsByUsername(
            @RequestHeader("X-Auth-Username") String username
    ) {
        List<ChatThreadResponseDto> response = chatThreadService.getThreadsByUsername(username).stream()
                .map(ChatThreadResponseDto::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "스레드 삭제", description = "스레드 ID를 기준으로 자신의 스레드를 삭제합니다. 다른 사용자의 스레드는 삭제할 수 없습니다.")
    @DeleteMapping("/{threadId}")
    public ResponseEntity<Void> deleteThread(
            @RequestHeader("X-Auth-Username") String username,
            @PathVariable Long threadId
    ) {
        chatThreadService.deleteThread(username, threadId);
        return ResponseEntity.noContent().build();
    }
}
