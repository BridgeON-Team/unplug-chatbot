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


    @Transactional
    public ChatMessageEntity sendMessage(ChatMessageDto dto) {
        ChatThreadEntity thread = chatThreadRepository.findById(dto.getThreadId())
                .orElseThrow(() -> new IllegalArgumentException("Thread not found"));


        ChatMessageEntity userMessage = ChatMessageEntity.toEntity(dto, thread);
        chatMessageRepository.save(userMessage);


        messagingTemplate.convertAndSend(
                "/topic/threads/" + thread.getThreadId(),
                ChatMessageDto.toDto(userMessage)
        );


        if (dto.getSender() == SenderStatus.USER) {
            String botReply = gptService.getChatbotResponse(dto.getMessage());


            ChatMessageDto botDto = new ChatMessageDto();
            botDto.setThreadId(dto.getThreadId());
            botDto.setUserId(-1L); // BOT ID convention
            botDto.setSender(SenderStatus.BOT);
            botDto.setMessage(botReply);


            ChatMessageEntity botMessage = ChatMessageEntity.toEntity(botDto, thread);
            chatMessageRepository.save(botMessage);


            messagingTemplate.convertAndSend(
                    "/topic/threads/" + thread.getThreadId(),
                    ChatMessageDto.toDto(botMessage)
            );
        }


        return userMessage;
    }


    @Transactional(readOnly = true)
    public List<ChatMessageEntity> getMessagesByThread(Long threadId) {
        return chatMessageRepository.findByChatThread_ThreadIdOrderByCreatedAtAsc(threadId);
    }


    @Transactional
    public void deleteMessage(Long messageId) {
        ChatMessageEntity msg = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
        chatMessageRepository.delete(msg);
    }
}