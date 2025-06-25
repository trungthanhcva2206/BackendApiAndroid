package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.MessageRequest;
import com.nhom1.BackEndMobile.dto.response.MessageResponse;
import com.nhom1.BackEndMobile.entity.Message;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.repository.MessageRepository;
import com.nhom1.BackEndMobile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageResponse sendMessage(MessageRequest request) {
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Message message = Message.builder()
                .sender(sender)
                .content(request.getContent())
                .build();

        Message saved = messageRepository.save(message);

        return new MessageResponse(
                saved.getId(),
                saved.getSender().getId(),
                saved.getContent(),
                sender.getFullname(),
                sender.getAvatar_url(),
                saved.getCreatedAt().toString()
        );
    }

    public List<MessageResponse> getAllMessages() {
        List<Message> messages = messageRepository.findAllByOrderByCreatedAtAsc();

        return messages.stream().map(m -> new MessageResponse(
                m.getId(),
                m.getSender().getId(),
                m.getContent(),
                m.getSender().getFullname(),
                m.getSender().getAvatar_url(),
                m.getCreatedAt().toString()
        )).toList();
    }
}
