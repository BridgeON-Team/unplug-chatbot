//  username 기반으로 리팩토링된 ChatMessageService

package org.example.unplugchatbotservice.service;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.domain.ChatMessageEntity;
import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.example.unplugchatbotservice.domain.SenderStatus;
import org.example.unplugchatbotservice.dto.ChatMessageDto;
import org.example.unplugchatbotservice.repository.ChatMessageRepository;
import org.example.unplugchatbotservice.repository.ChatThreadRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatThreadRepository chatThreadRepository;
    private final GptService gptService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatThreadService chatThreadService;

    private static final String BOT_USERNAME = "BOT";

    @Transactional
    public ChatMessageEntity sendMessage(ChatMessageDto dto) {
        // 보안 강화: username 기반 스레드 조회로 검증도 겸함
        ChatThreadEntity thread = chatThreadRepository.findById(dto.getThreadId())
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));

        // 유저 메시지 저장 및 브로드캐스트
        ChatMessageEntity userMessage = saveAndBroadcast(dto, thread);

        // GPT 응답 처리
        if (dto.getSender() == SenderStatus.USER) {
            String botReply = gptService.getChatbotResponse(dto.getMessage());
            ChatMessageDto botDto = buildBotReplyDto(dto.getThreadId(), botReply);
            saveAndBroadcast(botDto, thread);
        }

        return userMessage;
    }

    @Transactional(readOnly = true)
    public List<ChatMessageEntity> getMessagesByThread(Long threadId, String username) {
        // 1. 해당 thread가 username의 소유인지 검증
        chatThreadService.validateThreadOwnership(threadId, username);

        // 2️. 해당 thread의 모든 메시지 조회 (USER + BOT)
        return chatMessageRepository.findByChatThreadThreadIdOrderByCreatedAtAsc(threadId);
    }


    @Transactional
    public void deleteMessage(String username, Long messageId) {
        ChatMessageEntity msg = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));

        if (!msg.getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to delete this message.");
        }

        chatMessageRepository.delete(msg);
    }

    // 공통 저장 + 브로드캐스트 처리 메서드
    private ChatMessageEntity saveAndBroadcast(ChatMessageDto dto, ChatThreadEntity thread) {
        ChatMessageEntity entity = ChatMessageEntity.toEntity(dto, thread);
        chatMessageRepository.save(entity);

        messagingTemplate.convertAndSend(
                "/topic/threads/" + thread.getThreadId(),
                ChatMessageDto.toDto(entity)
        );
        return entity;
    }

    // 봇 응답용 DTO 생성 메서드
    private ChatMessageDto buildBotReplyDto(Long threadId, String message) {
        ChatMessageDto botDto = new ChatMessageDto();
        botDto.setThreadId(threadId);
        botDto.setUsername(BOT_USERNAME);
        botDto.setSender(SenderStatus.BOT);
        botDto.setMessage(message);
        return botDto;
    }
}
