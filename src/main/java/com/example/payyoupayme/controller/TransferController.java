package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.TransferService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    private final TransferService transferService;
    private final UtilisateurService utilisateurService;

    public TransferController(TransferService transferService, UtilisateurService utilisateurService) {
        this.transferService = transferService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping()
    public String getTransfer(Model model) {
        model.addAttribute("userConnected", utilisateurService.getCurrentUser());
        model.addAttribute("transfers", transferService.getAllTransferOfCurrentUser().getTransfer());
        return "transfer";
    }

    @PostMapping("/addBalance")
    public String addUserBalance(Model model, @RequestParam Float amount){
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        Float total = utilisateur.getBalance() + amount;

        utilisateur.setBalance(total);
        transferService.addBalanceToUser(amount, utilisateur);
        utilisateurService.updateCurrentUser(utilisateur);

        model.addAttribute("userConnected", utilisateur);
        model.addAttribute("transfers", transferService.getAllTransferOfCurrentUser().getTransfer());

        return "transfer";
    }

    @PostMapping("/externAccount")
    public String sendToExternCompte(Model model, @RequestParam Float amount){

        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        if (utilisateur.getBalance() < amount) {
            model.addAttribute("error", "Vous n'avez pas assez d'argent a envoyer vers votre compte externe");
            model.addAttribute("userConnected", utilisateur);
            model.addAttribute("transfers", transferService.getAllTransferOfCurrentUser().getTransfer());

            return "transfer";
        }
        transferService.sendToExternCompte(amount);
        model.addAttribute("userConnected", utilisateur);
        model.addAttribute("transfers", transferService.getAllTransferOfCurrentUser().getTransfer());

        return "transfer";
    }
}
