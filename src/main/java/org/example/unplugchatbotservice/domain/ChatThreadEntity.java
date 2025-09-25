package org.example.unplugchatbotservice.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.unplugchatbotservice.dto.ChatThreadDto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatThreadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;


    @Column(name = "user_id", nullable = false)
    private Long userId;


    @Column(length = 100)
    private String title;


    @Column(name = "created_date")
    private LocalDateTime createdDate;


    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @OneToMany(mappedBy = "chatThread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageEntity> chatMessages = new ArrayList<>();


    public static ChatThreadEntity toEntity(ChatThreadDto dto) {
        ChatThreadEntity chatThread = new ChatThreadEntity();
        chatThread.setUserId(dto.getUserId());
        chatThread.setTitle(dto.getTitle());
        chatThread.setCreatedDate(LocalDateTime.now());
        chatThread.setUpdateDate(LocalDateTime.now());
        return chatThread;
    }
}