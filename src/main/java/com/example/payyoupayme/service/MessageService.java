package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Message;
import com.example.payyoupayme.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }
}