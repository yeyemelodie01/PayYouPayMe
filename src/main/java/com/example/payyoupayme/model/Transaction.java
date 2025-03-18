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
    private String messageContent;
    private LocalDate date;

    @ManyToOne
    private Utilisateur sender;

    @ManyToOne
    private Utilisateur receiver;

    public Transaction(Float amount, String messageContent, LocalDate date) {
        this.amount = amount;
        this.messageContent = messageContent;
        this.date = date;
    }

    public Transaction(Float amount, String messageContent, LocalDate date, Utilisateur sender, Utilisateur receiver) {
        this.amount = amount;
        this.messageContent = messageContent;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Transaction{}";
    }
}
