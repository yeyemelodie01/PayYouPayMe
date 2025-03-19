package com.example.payyoupayme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactExterne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Float amount;

    private String iban;

    @ManyToOne
    private Utilisateur utilisateur;

    public TransactExterne(Float amount, String iban, Utilisateur utilisateur) {
        this.amount = amount;
        this.iban = iban;
        this.utilisateur = utilisateur;
    }

    //recevoir de l'argent d'un compte extérieur ou envoyer de l'argent vers un compte exterieur faire une nouvelle table avec l'iban mettand un nouveau model transaction externer qui aura juste une receveur qui sera lié a l'utilisateur

}
