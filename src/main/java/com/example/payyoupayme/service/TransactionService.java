package com.example.payyoupayme.service;

import com.example.payyoupayme.model.Transaction;
import com.example.payyoupayme.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}