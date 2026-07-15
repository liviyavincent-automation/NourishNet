package com.nourishnet.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nourishnet.ai.GeminiService;

@Service
public class ChatService {

    @Autowired
    private GeminiService geminiService;

    public String getResponse(String question) {

        return geminiService.askGemini(question);

    }

}