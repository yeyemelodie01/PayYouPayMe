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

    private String role;

    private boolean isActive = true;
    private boolean isBlocked = true;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Transaction> transactionSent = new ArrayList<Transaction>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Transaction> transactionReceived = new ArrayList<Transaction>();

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<Message> messageSent = new ArrayList<Message>();

    @ManyToMany
    private List<Utilisateur> contact = new ArrayList<Utilisateur>();

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private List<Transfer> transfer = new ArrayList<Transfer>();

    public Utilisateur(String username, String lastName, String firstName, String email, String iban, Float balance, String login, String password, String role, boolean isActive, boolean isBlocked, List<Transaction> transactionSent, List<Transaction> transactionReceived, List<Message> messageSent, List<Utilisateur> contact, List<Transfer> transfer) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.iban = iban;
        this.balance = balance;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.isBlocked = isBlocked;
        this.transactionSent = transactionSent;
        this.transactionReceived = transactionReceived;
        this.messageSent = messageSent;
        this.contact = contact;
        this.transfer = transfer;
    }

    @Override
    public String toString() {
        return "Utilisateur{}";
    }
}
