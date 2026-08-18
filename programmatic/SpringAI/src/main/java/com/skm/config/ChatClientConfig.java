package com.skm.config;

import com.skm.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        var options = ChatOptions.builder().temperature(0.8);
        return builder
                .defaultOptions(options)
                .defaultSystem("You are a IT assistant. Answer in English. If you don't know the answer, say: I don't know. If the question is not related to IT, say: I don't know.")
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        new TokenUsageAuditAdvisor()))
                .build();
    }
}