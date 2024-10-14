package com.example.springboottest.controller;

import com.example.springboottest.service.LlamaAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ai.chat.client.ChatClient;

//import org.springframework.ai.openai.OpenAiChatModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:9000")
public class ChatController {

    @Autowired
    private LlamaAPIService llamaAPIService;

    private final ChatClient chatClient;
//    private final OpenAiChatModel openAiChatModel;

    public ChatController(ChatClient.Builder chatClientBuilder) { // ,OpenAiChatModel openAiChatModel
        this.chatClient = chatClientBuilder.build();
//        this.openAiChatModel = openAiChatModel;
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

    @GetMapping("/llama")
    public String getBody() throws JsonProcessingException {
        return llamaAPIService.chatWithAI();
    }

//    @GetMapping("/openai")
//    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        System.out.println(openAiChatModel.call(message));
//        return "sucess";
//    }

}