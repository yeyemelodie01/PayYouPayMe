package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateur() { return utilisateurRepository.findAll(); }
}
