package org.example.unplugchatbotservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI unplugChatbotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Unplug Chatbot Service API")
                        .description("Spring Boot 기반 챗봇 MSA 서비스의 REST API 문서")
                        .version("v1.0.0"));
    }
}
