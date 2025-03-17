package com.example.payyoupayme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String iban;

    private Float balance;

    private String login;
    private String password;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> transactionSent;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionReceived;

    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messageSent;

    @ManyToMany
    private List<Utilisateur> contact;
}
