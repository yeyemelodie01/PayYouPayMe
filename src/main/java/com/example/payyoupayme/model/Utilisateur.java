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

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String iban;
    private Float balance;

    @ManyToMany
    private List<Utilisateur> contact;

    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messageSent;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> transactionSent;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionReceived;

    public Utilisateur(String firstName, String lastName, String email, String login, String password, String iban, Float balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.iban = iban;
        this.balance = balance;
        this.contact = new ArrayList<Utilisateur>();
        this.messageSent = new ArrayList<Message>();
        this.transactionSent = new ArrayList<Transaction>();
        this.transactionReceived = new ArrayList<Transaction>();
    }
}
