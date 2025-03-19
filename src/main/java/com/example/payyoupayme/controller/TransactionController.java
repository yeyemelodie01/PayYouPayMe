package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.TransactionService;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final UtilisateurService utilisateurService;

    public TransactionController(TransactionService transactionService, UtilisateurService utilisateurService) {
        this.transactionService = transactionService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping()
    public String getAllTransaction(Model model) {
        model.addAttribute("userConnected", utilisateurService.getCurrentUser());
        return "transaction";
    }

    @PostMapping("/createtransaction")
    public String createTransaction(Model model, @RequestParam Float amount, @RequestParam String message, @RequestParam Integer receiver) {

        Utilisateur userConnected = utilisateurService.getCurrentUser();
        Utilisateur userReceiver = utilisateurService.findById(receiver).orElse(null);

        if( amount > userConnected.getBalance() && amount < 0){
            model.addAttribute("error", "Vous n'avez pas assez d'argent");
            model.addAttribute("transactions", transactionService.getAllTransaction());
            model.addAttribute("userConnected", utilisateurService.getCurrentUser());

            return "transaction";
        }
        Float amountTotal = userConnected.getBalance() - amount;
        Float amountReceiver = userReceiver.getBalance() + amount;

        userReceiver.setBalance(amountReceiver);
        userConnected.setBalance(amountTotal);

        utilisateurService.updateCurrentUser(userReceiver);
        utilisateurService.updateCurrentUser(userConnected);
        transactionService.addTransaction(amount, message, userConnected, userReceiver);

        model.addAttribute("transactions", transactionService.getAllTransaction());
        model.addAttribute("userConnected", utilisateurService.getCurrentUser());

        return "transaction";
    }
}