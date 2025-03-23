package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.TransferService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/me/user")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final TransferService transferService;

    public UtilisateurController(UtilisateurService utilisateurService, TransferService transferService) {
        this.utilisateurService = utilisateurService;
        this.transferService = transferService;
    }

    @GetMapping("/")
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

    @PostMapping("/enable")
    public String disableCurrentUser() {
        utilisateurService.disableCurrentUser();
        return "index";
    }
}