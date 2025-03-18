package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/me/user")
    public String getCurrentUser(Model model) {
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        model.addAttribute("userToDisplay", utilisateur);
        return "userview";
    }

    @PostMapping("/update")
    public String updateCurrentUser(@ModelAttribute Utilisateur utilisateur) {
       utilisateurService.updateCurrentUser(utilisateur);
        return "redirect:/utilisateur/me/user";
    }
}