package com.beyond.chatbotai02.ClientServiceTest;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.qianfan.QianfanChatModel;
import dev.langchain4j.service.AiServices;


//主要用于连续对话测试
public class QianfanChatWithOnePersonMemory {
    public static void main(String[] args) {
        // 创建一个QianfanChatModel对象，并通过builder模式设置其属性
        QianfanChatModel model = QianfanChatModel.builder()
                // 设置API密钥
                .apiKey("")
                // 设置密钥
                .secretKey("")
                // 设置模型名称
                .modelName("")
                // 构建并返回ChatModel对象
                .build();
        // 创建一个ChatMemory对象，用于存储聊天消息的窗口
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                // 设置聊天窗口中最多存储的消息数量为10
                .maxMessages(10)
                // 构建并返回ChatMemory对象
                .build();
        // 创建一个IAiService类型的AI服务助手实例
        IAiService assistant = AiServices.builder(IAiService.class)
                // 设置聊天语言模型，使用之前创建的model对象
                .chatLanguageModel(model)
                // 设置聊天记忆体，使用之前创建的chatMemory对象
                .chatMemory(chatMemory)
                // 构建并返回IAiService实例
                .build();
        //示例输入输出
        String answer = assistant.chat("Hello,my name is xiaoyu");
        System.out.println(answer); // Hello xiaoyu!******

        String answerWithName = assistant.chat("What's my name?");
        System.out.println(answerWithName); // Your name is xiaoyu.

        String answer1 = assistant.chat("I like playing football.");
        System.out.println(answer1); // The answer

        String answer2 = assistant.chat("I want to go eat delicious food.");
        System.out.println(answer2); // The answer

        String answerWithLike = assistant.chat("What I like to do?");
        System.out.println(answerWithLike);//Playing football.******
    }
}
