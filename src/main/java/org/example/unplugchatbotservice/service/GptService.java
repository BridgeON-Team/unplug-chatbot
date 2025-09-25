package org.example.unplugchatbotservice.service;

import lombok.RequiredArgsConstructor;
import org.example.unplugchatbotservice.dto.response.ChatGptResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GptService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1")
            .build();

    @Value("${openai.api.key}")
    private String apiKey;

    public String getChatbotResponse(String userMessage) {
        ChatGptResponseDto response = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(buildRequest(userMessage))
                .retrieve()
                .bodyToMono(ChatGptResponseDto.class)
                .block(); // 동기 처리

        // ✅ GPT 응답 메시지 추출
        return response.getChoices().get(0).getMessage().getContent();
    }

    private String buildRequest(String message) {
        return """
        {
          "model": "gpt-3.5-turbo",
          "messages": [
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": "%s"}
          ]
        }
        """.formatted(message);
    }
}
