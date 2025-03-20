package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Message;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UtilisateurService utilisateurService;

    public MessageService(MessageRepository messageRepository, UtilisateurService utilisateurService) {
        this.messageRepository = messageRepository;
        this.utilisateurService = utilisateurService;
    }

    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    public List<Message> getMessageFromCurrentUser() {
        return utilisateurService.getCurrentUser().getMessageSent();
    }

    public void addMessage(String content) {
        Utilisateur user = utilisateurService.getCurrentUser();
        Message message = new Message(content, LocalDate.now(), null, user);
        messageRepository.save(message);
    }

    public void replyToMessage(String messageReply, int messageId) {
        Message messageSend = messageRepository.findById(messageId).orElse(null);
        if (messageSend != null){
            messageSend.setResponse(messageReply);
            messageRepository.save(messageSend);
        }
    }
}