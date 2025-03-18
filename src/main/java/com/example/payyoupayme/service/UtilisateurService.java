package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final AuthentificationService authService;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, AuthentificationService authService) {
        this.utilisateurRepository = utilisateurRepository;
        this.authService = authService;
    }

    public List<Utilisateur> getAllUtilisateur() { return utilisateurRepository.findAll(); }

    public Optional<Utilisateur> findByLogin(String username) {
        return utilisateurRepository.findByLogin(username);
    }

    public Optional<Utilisateur> findById(Integer userId) {
        return utilisateurRepository.findById(userId);
    }

    public Utilisateur getCurrentUser() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username); /* Optionnal c'est une boite qui va contenir des utilisateurs ou rien */
            return utilisateur.orElse(null);
        }
        return null;
    }

    public void updateCurrentUser(Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.findById(utilisateur.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        existingUser.setUsername(utilisateur.getUsername());
        existingUser.setFirstName(utilisateur.getFirstName());
        existingUser.setLastName(utilisateur.getLastName());
        existingUser.setEmail(utilisateur.getEmail());
        existingUser.setIban(utilisateur.getIban());
        existingUser.setBalance(utilisateur.getBalance());

        utilisateurRepository.save(existingUser);
    }
}
