package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.TransactionRepository;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AuthentificationService authService;

    public TransactionService(TransactionRepository transactionRepository, UtilisateurRepository utilisateurRepository, AuthentificationService authService) {
        this.transactionRepository = transactionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.authService = authService;
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Utilisateur getCurrentUser() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username); /* Optionnal c'est une boite qui va contenir des utilisateurs ou rien */
            return utilisateur.orElse(null);
        }
        return null;
    }

   /* public List<Transaction> getCurrentUserTransactions() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            return transactionRepository.findAllByUserLogin(username);
        }
        return Collections.emptyList();
    }*/

   /* public List<Transaction> getUserTransactions(Integer userId) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(userId);
        return utilisateur.map(u -> transactionRepository.findAllByUser(u))
                .orElse(Collections.emptyList());
    }*/
}