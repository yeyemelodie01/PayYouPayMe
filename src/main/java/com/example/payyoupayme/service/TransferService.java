package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Transfer;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final UtilisateurService utilisateurService;

    public TransferService(TransferRepository transferRepository, UtilisateurService utilisateurService) {
        this.transferRepository = transferRepository;
        this.utilisateurService = utilisateurService;
    }

    public void addBalanceToUser(Float amount, Utilisateur utilisateur) {
        transferRepository.save(new Transfer(amount, utilisateur.getIban(), LocalDate.now(), utilisateur));
    }

    public Utilisateur getAllTransferOfCurrentUser() {
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        return utilisateur;
    }

    public void sendToExternCompte(Float amount) {
        Utilisateur utilisateur = utilisateurService.getCurrentUser();
        Float total = utilisateur.getBalance() - amount;

        utilisateur.setBalance(total);
        utilisateurService.updateCurrentUser(utilisateur);
        transferRepository.save(new Transfer(amount, utilisateur.getIban(), LocalDate.now(), utilisateur));
    }
}
