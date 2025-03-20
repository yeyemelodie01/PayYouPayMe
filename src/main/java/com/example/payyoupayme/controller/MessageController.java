package com.example.payyoupayme.controller;

import com.example.payyoupayme.service.MessageService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final UtilisateurService utilisateurService;

    public MessageController(MessageService messageService, UtilisateurService utilisateurService) {
        this.messageService = messageService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping()
    public String getMessage(Model model) {
        model.addAttribute("messages", messageService.getMessageFromCurrentUser());

        return "message";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(Model model, @RequestParam String content) {
        messageService.addMessage(content);
        model.addAttribute("messages", messageService.getMessageFromCurrentUser());

        return "message";
    }
}