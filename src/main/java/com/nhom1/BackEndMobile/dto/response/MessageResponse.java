package com.nhom1.BackEndMobile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String id;
    private String senderId;
    private String content;
    private String senderName;
    private String senderAvatar;
    private String createdAt;
}
