package com.beyond.chatbotai02.Controller;

import com.beyond.chatbotai02.Service.ChatService;
import com.beyond.chatbotai02.dto.ChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    @Autowired
    private ChatService chatGptService;

    @PostMapping ("/chat")
    @CrossOrigin(origins = "*")
    public String chatWithGpt(@RequestBody ChatRequest request) throws UnsupportedEncodingException {

        String query = request.getQuery();
        //判空处理，防止输入不合理值
        if(request==null && request.getQuery()=="" ){
            query="你好";
        }
        //预期就是回复"有什么可以帮助你"
        return chatGptService.chatWithGpt(query);

    }

    // 定义一个名为IndexController的REST控制器类
    @RestController
    // 允许所有来源的跨域请求
    @CrossOrigin(origins = "*")
    public class IndexController {
        // 定义一个映射到"/gpt"和"/"路径的请求处理方法
        @RequestMapping({"/gpt", "/"})
        // 方法返回一个ModelAndView对象，指定视图名称为"index"
        public ModelAndView gpt() {
            return new ModelAndView("index");
        }
    }



}






