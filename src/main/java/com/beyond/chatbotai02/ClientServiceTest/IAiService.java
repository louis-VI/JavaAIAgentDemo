package com.beyond.chatbotai02.ClientServiceTest;

import dev.langchain4j.service.UserMessage;

public interface IAiService {
    /**
     * Ai Services 提供了一种更简单、更灵活的替代方案。 您可以定义自己的 API（具有一个或多个方法的 Java 接口）， 并将为其提供实现。
     *
     *
     * @param userMessage
     * @return String
     */

    String chat(@UserMessage String userMessage);
}
