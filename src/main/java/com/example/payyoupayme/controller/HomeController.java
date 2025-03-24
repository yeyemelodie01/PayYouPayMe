package com.example.payyoupayme.controller;

import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.service.UtilisateurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;

    public HomeController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping()
    public String getHome() {
        return "index";
    }

    @PostMapping("signup")
    public String signup(Model model, @RequestParam String username, @RequestParam String password) {
        Utilisateur userToSave = new Utilisateur();
        userToSave.setUsername(username);
        userToSave.setPassword(passwordEncoder.encode(password));
        userToSave.setActive(true);
        userToSave.setBalance(0f);
        userToSave.setBlocked(true);
        userToSave.setRole("USER");
        utilisateurService.createUtilisateur(userToSave);
        return "home";
    }
}
