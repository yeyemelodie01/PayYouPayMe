package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
    public String addContact(Model model, @RequestParam("username") String username) {
        utilisateurService.addUserToContactList(username);
        model.addAttribute("contactUser", utilisateurService.getCurrentUser().getContact());
        return "contact";
    }

    @PostMapping("/delete")
    public String deleteContact(Model model, @RequestParam Integer id) {
        System.out.println(id);
        utilisateurService.deleteContactById(id);
        model.addAttribute("contactUser", utilisateurService.getCurrentUser().getContact());
        return "contact";
    }
}
