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
        return utilisateurRepository.findByUsername(username);
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

        existingUser.setFirstName(utilisateur.getFirstName());
        existingUser.setLastName(utilisateur.getLastName());
        existingUser.setEmail(utilisateur.getEmail());

        utilisateurRepository.save(existingUser);
    }

    public void checkContact(String username) {
        Utilisateur currentUser = getCurrentUser(); //récupère l'utilisateur connecter
        Optional<Utilisateur> contact = utilisateurRepository.findByUsername(username); //récupère l'utilisateur rechercher
        if (contact.isPresent() && !currentUser.getContact().contains(contact.get())) { //si la liste de contact existe et si dans la liste l'user rentrer n'est pas présent
            currentUser.getContact().add(contact.get()); //alors on ajoute dans la liste de nouveau contact
            utilisateurRepository.save(currentUser); //on enreggistre les modification ddans la bdd
        }
    }

    public Utilisateur getUserByUsername(String username) {
        return utilisateurRepository.findByUsername(username).orElse(null);
    }

    public void addUserToContactList(String username) {
        Utilisateur userConnected = getCurrentUser(); //récupère l'utilisateur connecter
        Utilisateur userToAdd = getUserByUsername(username); //récupère l'utilisateur rechercher
        if (userToAdd != null && userToAdd != userConnected && !userConnected.getContact().contains(userToAdd)) { //si la liste de contact existe et si dans la liste l'user rentrer n'est pas présent
            userConnected.getContact().add(userToAdd); //alors on ajoute dans la liste de nouveau contact
            utilisateurRepository.save(userConnected);
        }
    }

    public void deleteContactById(int id) {
        Utilisateur userConnected = getCurrentUser();
        Utilisateur contactToDelete = utilisateurRepository.findById(id).orElse(null);

        if (contactToDelete != null){
            userConnected.getContact().remove(contactToDelete);
        }
        utilisateurRepository.save(userConnected);
    }
}
