package com.example.microservices.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.transactionservice.model.Transaction;
import com.example.microservices.transactionservice.repository.TransactionRepository;



@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	
	public List<Transaction> getAllTransactions() {
	    return transactionRepository.findAll();
	}

	public Transaction recordTransaction(Transaction transaction) {
		transaction.setTimestamp(LocalDateTime.now());
		return transactionRepository.save(transaction);
	}
	
	public List<Transaction> getTransactionsByAccountId(Long accountId){
		return transactionRepository.findByAccountId(accountId);
	}
	}
