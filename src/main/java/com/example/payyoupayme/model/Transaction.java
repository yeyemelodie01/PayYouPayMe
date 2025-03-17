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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Float amount;
    private LocalDate date;
    private String messageContent;

    @ManyToOne
    private Utilisateur sender;

    @ManyToOne
    private Utilisateur receiver;

    public Transaction(Float amount, LocalDate date, String messageContent, Utilisateur sender, Utilisateur receiver) {
        this.amount = amount;
        this.date = date;
        this.messageContent = messageContent;
        this.sender = sender;
        this.receiver = receiver;
    }
}
