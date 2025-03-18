package com.example.payyoupayme.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    /**
     * Récupère le nom d'userToDisplay actuellement connecté
     * @return Le nom d'userToDisplay (login) ou null si aucun userToDisplay n'est connecté
     */
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
