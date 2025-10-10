// ✅ username 기반으로 리팩토링된 ChatMessageEntity

package org.example.unplugchatbotservice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.unplugchatbotservice.dto.ChatMessageDto;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private ChatThreadEntity chatThread;

    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private SenderStatus sender;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static ChatMessageEntity toEntity(ChatMessageDto dto, ChatThreadEntity thread) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setUsername(dto.getUsername());
        entity.setChatThread(thread);
        entity.setSender(dto.getSender());
        entity.setMessage(dto.getMessage());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
}