package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.MessageService;
import com.example.payyoupayme.service.TransactionService;
import com.example.payyoupayme.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UtilisateurService utilisateurService;
    private final TransactionService transactionService;
    private final MessageService messageService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UtilisateurService utilisateurService, TransactionService transactionService, MessageService messageService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.transactionService = transactionService;
        this.messageService = messageService;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute Utilisateur user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hacher le mot de passe
        user.setRole("USER"); // Rôle par défaut
        utilisateurService.createUtilisateur(user);
        return "redirect:/login"; // Rediriger vers la page de connexion
    }

}
