package com.beyond.chatbotai02.Service.Impl;

import com.beyond.chatbotai02.Service.ChatService;
import dev.langchain4j.chain.ConversationalChain;
//import dev.langchain4j.model.openai.OpenAiChatModel;
//import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.qianfan.QianfanChatModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
//websocket 长连接建立
@SessionScope
public class ChatServiceImpl implements ChatService {
    // 定义一个私有的ConversationalChain类型的成员变量chain
    private final ConversationalChain chain;
    // 构造函数
    public ChatServiceImpl() {
        // 创建一个QianfanChatModel对象，这里使用空字符串作为API密钥、密钥和模型名称，实际使用时应该填入有效的值
        QianfanChatModel model = QianfanChatModel.builder()
                .apiKey("")
                .secretKey("")
                .modelName("")
                .build();

        // 创建一个ChatMemory对象，设置最大消息数量为1000
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(1000)
                .build();

        // 初始化chain成员变量，传入之前创建的语言模型和聊天记忆体对象
        this.chain = ConversationalChain.builder()
                .chatLanguageModel(model)
                .chatMemory(chatMemory)
                .build();


    }
    // 实现chatWithGpt方法，接收用户消息作为参数
    @Override
    public String chatWithGpt(String userMessage) {
        return chain.execute(userMessage);

    }
}