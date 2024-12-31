package com.beyond.chatbotai02.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//解决公网向外发布时候的跨域问题
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // 允许的客户端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的方法
                .allowedHeaders("*");  // 您可以根据需要限制此项
        registry.addMapping("/chat/**")
                .allowedOrigins("*")  // 允许的客户端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
