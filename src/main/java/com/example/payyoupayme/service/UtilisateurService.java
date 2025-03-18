package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateur() { return utilisateurRepository.findAll(); }

    public Optional<Utilisateur> findByLogin(String username) {
        return utilisateurRepository.findByLogin(username);
    }

    public Optional<Utilisateur> findById(Integer userId) {
        return utilisateurRepository.findById(userId);
    }
}
