package com.example.springboottest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:9000")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public String getResponseFromAI(@RequestBody String userMessage) {
        // AI 모델에 메시지 전달하고 응답 받기
        String response = chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
        System.out.println("yes");
        return response;
    }

}