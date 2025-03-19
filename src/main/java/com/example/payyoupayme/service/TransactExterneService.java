package com.example.payyoupayme.service;

import com.example.payyoupayme.model.TransactExterne;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.TransactExterneRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactExterneService {

    private final TransactExterneRepository transactExterneRepository;

    public TransactExterneService(TransactExterneRepository transactExterneRepository) {
        this.transactExterneRepository = transactExterneRepository;
    }

    public void addBalanceToUser(Float amount, Utilisateur utilisateur) {
        transactExterneRepository.save(new TransactExterne(amount, utilisateur.getIban(), utilisateur));
    }

    public void addAmountToExternAccount(Float amount, Utilisateur utilisateur) {
        transactExterneRepository.save(new TransactExterne(amount, "987654321", utilisateur));
    }
}
