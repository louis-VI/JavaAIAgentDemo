package com.beyond.chatbotai02.dto;

public class ChatRequest {

    // 定义一个私有的字符串变量query，用于存储聊天请求中的查询内容
        private String query;
    // 默认无参构造函数
        public ChatRequest() {

        }
    // 带有一个参数的构造函数，用于初始化查询内容
        public ChatRequest(String query) {
            this.query = query;
        }
    // 获取查询内容的公共方法
        public String getQuery() {
            return query;
        }
    // 设置查询内容的公共方法
        public void setQuery(String query) {
            this.query = query;
        }



}
