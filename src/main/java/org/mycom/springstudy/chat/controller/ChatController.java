package org.mycom.springstudy.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.chat.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {
    @MessageMapping("/chat.sendMessage") // 클라이언트가 메시지를 보낼 endpoint
    @SendTo("/topic/public") // 메시지를 받을 구독자에게 전송할 destination
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }
}
