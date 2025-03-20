package com.example.payyoupayme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    private LocalDate date;

    private String response;

    @ManyToOne
    private Utilisateur utilisateur;

    public Message(String content, LocalDate date, String response, Utilisateur utilisateur) {
        this.content = content;
        this.date = date;
        this.response = response;
        this.utilisateur = utilisateur;
    }

    public Message(String content, LocalDate date, String response) {
        this.content = content;
        this.date = date;
        this.response = response;
    }
}
