package com.example.payyoupayme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String lastName;

    private String firstName;

    private String email;

    private String iban;

    private Float balance;

    private String login;
    private String password;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Transaction> transactionSent;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Transaction> transactionReceived;

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<Message> messageSent;

    @ManyToMany
    private List<Utilisateur> contact;

    public Utilisateur(String username, String lastName, String firstName, String email, String iban, Float balance, String login, String password, List<Transaction> transactionSent, List<Transaction> transactionReceived, List<Message> messageSent, List<Utilisateur> contact) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.iban = iban;
        this.balance = balance;
        this.login = login;
        this.password = password;
        this.transactionSent = transactionSent;
        this.transactionReceived = transactionReceived;
        this.messageSent = messageSent;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Utilisateur{}";
    }
}
