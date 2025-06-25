package com.nhom1.BackEndMobile.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String senderId;
    private String content;
}
