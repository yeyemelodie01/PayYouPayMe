package com.example.payyoupayme.controller;

import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final UtilisateurService utilisateurService;

    public ContactController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public String getContacts(Model model) {
        model.addAttribute("contactUser", utilisateurService.getCurrentUser().getContact());
        return "contact";
    }

    @PostMapping("/add")
    public String addContact(Model model, @RequestParam String username) {
        utilisateurService.checkContact(username);
        model.addAttribute("contactUser", utilisateurService.getCurrentUser().getContact());
        return "redirect:/contact";
    }
}
