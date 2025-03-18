package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AuthentificationService authService;

    public TransactionService(TransactionRepository transactionRepository, AuthentificationService authService) {
        this.transactionRepository = transactionRepository;
        this.authService = authService;
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
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