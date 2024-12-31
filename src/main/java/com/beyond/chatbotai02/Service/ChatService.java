package com.beyond.chatbotai02.Service;



import dev.langchain4j.service.UserMessage;
import org.springframework.stereotype.Service;

//注意interface不需要@Service
public interface ChatService {

//    String chatWithGpt(String query);
    String chatWithGpt(@UserMessage String userMessage);

}
