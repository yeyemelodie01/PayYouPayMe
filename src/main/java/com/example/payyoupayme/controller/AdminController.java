package com.example.payyoupayme.controller;

import com.example.payyoupayme.service.MessageService;
import com.example.payyoupayme.service.TransactionService;
import com.example.payyoupayme.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UtilisateurService utilisateurService;
    private final TransactionService transactionService;
    private final MessageService messageService;

    public AdminController(UtilisateurService utilisateurService, TransactionService transactionService, MessageService messageService) {
        this.utilisateurService = utilisateurService;
        this.transactionService = transactionService;
        this.messageService = messageService;
    }

    @GetMapping()
    public String GetHello() {
        return "hello";
    }

    @GetMapping("alldata")
    public String getAllData(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateur());
        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("messages", messageService.getAllMessage());
        return "alldata";
    }

    @PostMapping("replytomessage")
    public String replyToMessage(Model model, @RequestParam String reply, @RequestParam int messageId) {
        messageService.replyToMessage(reply, messageId);

        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("messages", messageService.getAllMessage());

        return "alldata";
    }
}
