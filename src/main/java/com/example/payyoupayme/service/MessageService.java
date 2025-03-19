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

    public void addMessage(String content, String username) {
        Utilisateur user = utilisateurService.getUserByUsername(username);
        Message message = new Message(content, LocalDate.now(), user);
        messageRepository.save(message);
    }
}