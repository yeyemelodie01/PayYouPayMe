package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.TransactExterneService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/me/user")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final TransactExterneService transactExterneService;

    public UtilisateurController(UtilisateurService utilisateurService, TransactExterneService transactExterneService) {
        this.utilisateurService = utilisateurService;
        this.transactExterneService = transactExterneService;
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

    @PostMapping("/addBalance")
    public String addUserBalance(Model model, @RequestParam Float amount){
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        Float total = utilisateur.getBalance() + amount;

        utilisateur.setBalance(total);
        transactExterneService.addBalanceToUser(amount, utilisateur);
        utilisateurService.updateCurrentUser(utilisateur);

        model.addAttribute("userToDisplay", utilisateur);
        return "userview";
    }

    @PostMapping("/externAccount")
    public String sendToExternCompte(Model model, @RequestParam Float amount){
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        Float total = utilisateur.getBalance() - amount;

        utilisateur.setBalance(total);
        transactExterneService.addAmountToExternAccount(amount, utilisateur);
        utilisateurService.updateCurrentUser(utilisateur);
        model.addAttribute("userToDisplay", utilisateur);
        return "userview";
    }
}