package com.example.payyoupayme.data;

import com.example.payyoupayme.model.Message;
import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.model.Utilisateur;
import com.example.payyoupayme.repository.MessageRepository;
import com.example.payyoupayme.repository.TransactionRepository;
import com.example.payyoupayme.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UtilisateurRepository utilisateurRepository,
                                   MessageRepository messageRepository,
                                   TransactionRepository transactionRepository) {
        return args -> {
            Utilisateur u1 = new Utilisateur();
            u1.setFirstName("firstNameU1");
            u1.setLastName("LastNameU1");
            u1.setUsername("usertest");
            u1.setEmail("usertest@gmail.com");
            u1.setIban("123456789");
            u1.setLogin("usertest");
            //	u1.setPassword(passwordEncoder.encode("user"));
            u1.setBalance(30f);
            utilisateurRepository.save(u1);

            Utilisateur u2 = new Utilisateur();
            u2.setFirstName("firstNameU2");
            u2.setLastName("LastNameU2");
            u2.setUsername("usertest2");
            u2.setEmail("usertest2@gmail.com");
            u2.setIban("12345678910");
            u2.setLogin("usertest2");
            //	u2.setPassword(passwordEncoder.encode("user"));
            u2.setBalance(20f);
            utilisateurRepository.save(u2);

            Utilisateur u3 = new Utilisateur();
            u3.setFirstName("firstNameU3");
            u3.setLastName("LastNameU3");
            u3.setUsername("usertest3");
            u3.setEmail("usertest2@gmail.com");
            u3.setIban("12345678910");
            u3.setLogin("usertest3");
            //	u2.setPassword(passwordEncoder.encode("user"));
            u3.setBalance(20f);
            utilisateurRepository.save(u3);

            u1.getContact().add(u2);
            utilisateurRepository.save(u1);


            Transaction t1 = new Transaction(20f, "pour les courses", LocalDate.now());
            t1.setSender(u1);
            t1.setReceiver(u2);
            transactionRepository.save(t1);

            Transaction t2 = new Transaction(1000f, "remboursement", LocalDate.now());
            t2.setSender(u2);
            t2.setReceiver(u1);
            transactionRepository.save(t2);

            Message m1 = new Message("message 1", LocalDate.now());
            m1.setUtilisateur(u1);
            messageRepository.save(m1);

            Message m2 = new Message("message 1", LocalDate.now());
            m2.setUtilisateur(u2);
            messageRepository.save(m2);
        };
    }
}
