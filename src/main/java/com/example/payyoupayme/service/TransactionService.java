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

    public void createTransaction(Float amount, String message, String username) {
        Utilisateur sender = utilisateurService.getCurrentUser();
        Utilisateur receiver = utilisateurService.getUserByUsername(username);

        if(sender.getBalance() > amount && amount > 0 && receiver!=null) {
            Transaction transactionToCreate = new Transaction();
            transactionToCreate.setAmount(amount);
            transactionToCreate.setDate(LocalDate.now());
            transactionToCreate.setMessageContent(message);
            transactionToCreate.setReceiver(receiver);
            transactionToCreate.setSender(sender);

            transactionRepository.save(transactionToCreate);
            receiver.setBalance(receiver.getBalance()+amount);
            sender.setBalance(sender.getBalance()-amount);
            utilisateurService.updateCurrentUser(receiver);
            utilisateurService.updateCurrentUser(sender);
        }
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