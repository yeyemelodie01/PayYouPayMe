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

    @ManyToOne
    private Utilisateur utilisateur;

    public Message(String content, LocalDate date, Utilisateur utilisateur) {
        this.content = content;
        this.date = date;
        this.utilisateur = utilisateur;
    }

    public Message(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }
}
