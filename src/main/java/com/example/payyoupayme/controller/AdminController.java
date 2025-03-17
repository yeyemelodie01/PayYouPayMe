package com.example.payyoupayme.controller;

import com.example.payyoupayme.service.MessageService;
import com.example.payyoupayme.service.TransactionService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {
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
}
