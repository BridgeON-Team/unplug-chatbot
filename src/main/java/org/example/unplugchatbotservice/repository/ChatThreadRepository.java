// ✅ username 기반으로 리팩토링된 ChatThreadRepository

package org.example.unplugchatbotservice.repository;

import org.example.unplugchatbotservice.domain.ChatThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatThreadRepository extends JpaRepository<ChatThreadEntity, Long> {

    // 특정 유저의 모든 채팅 스레드
    List<ChatThreadEntity> findByUsername(String username);

    // 특정 유저의 특정 스레드 (소유자 검증 목적)
    Optional<ChatThreadEntity> findByThreadIdAndUsername(Long threadId, String username);

    // (선택) 제목 중복 방지
    boolean existsByUsernameAndTitle(String username, String title);

    // (선택) 최신순 정렬
    List<ChatThreadEntity> findByUsernameOrderByUpdateDateDesc(String username);
}

