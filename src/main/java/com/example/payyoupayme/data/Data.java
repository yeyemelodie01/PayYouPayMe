package com.example.payyoupayme.data;

import com.example.payyoupayme.model.Message;
import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.MessageRepository;
import com.example.payyoupayme.repository.TransactionRepository;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Data {
    private final UtilisateurRepository utilisateurRepository;
    private final TransactionRepository transactionRepository;
    private final MessageRepository messageRepository;

    public Data(UtilisateurRepository utilisateurRepository, TransactionRepository transactionRepository, MessageRepository messageRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.transactionRepository = transactionRepository;
        this.messageRepository = messageRepository;
    }

    public void insertData() {
        Utilisateur u1 = new Utilisateur();
        u1.setFirstName("Test1");
        u1.setLastName("Test1");
        u1.setBalance(78.52f);
        utilisateurRepository.save(u1);

        Utilisateur u2 = new Utilisateur();
        u2.setFirstName("Test2");
        u2.setLastName("Test2");
        u2.setBalance(20f);
        utilisateurRepository.save(u1);

        Transaction t1 = new Transaction(12.50f, LocalDate.now(), "message test", u1, u2);
        this.transactionRepository.save(t1);

        Message m1 = new Message("message1", LocalDate.now());
        m1.setUtilisateur(u1);
        this.messageRepository.save(m1);

    }
}
