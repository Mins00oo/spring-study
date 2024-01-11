package org.mycom.springstudy.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    // 메시지 타입 : 입장, 채팅, 나감
    private String content;
    private String sender;
}