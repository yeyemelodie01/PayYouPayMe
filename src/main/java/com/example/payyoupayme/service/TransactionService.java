package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AuthentificationService authService;
    private final UtilisateurService utilisateurService;

    public TransactionService(TransactionRepository transactionRepository, AuthentificationService authService, UtilisateurService utilisateurService) {
        this.transactionRepository = transactionRepository;
        this.authService = authService;
        this.utilisateurService = utilisateurService;
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public void addTransaction(Float amount, String message, Utilisateur userConnected, Utilisateur userReceiver) {
        Transaction transaction = new Transaction(amount, message, LocalDate.now(), userConnected, userReceiver);
        transactionRepository.save(transaction);
    }



   /* public List<Transaction> getCurrentUserTransactions() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            return transactionRepository.findAllByUserLogin(username);
        }
        return Collections.emptyList();
    }*/

   /* public List<Transaction> getUserTransactions(Integer userId) {
        Optional<Utilisateur> userToDisplay = utilisateurRepository.findById(userId);
        return userToDisplay.map(u -> transactionRepository.findAllByUser(u))
                .orElse(Collections.emptyList());
    }*/
}