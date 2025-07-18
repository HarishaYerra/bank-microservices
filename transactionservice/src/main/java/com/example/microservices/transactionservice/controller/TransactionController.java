package com.example.microservices.transactionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.transactionservice.model.Transaction;
import com.example.microservices.transactionservice.service.TransactionService;



@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	private final TransactionService transactionService;
	
	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
	    List<Transaction> transactions = transactionService.getAllTransactions();
	    return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Transaction> recordTransaction(@RequestBody Transaction transaction){
		Transaction savedTransaction = transactionService.recordTransaction(transaction);
		return new ResponseEntity<>(savedTransaction,HttpStatus.CREATED);
	}
	
	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionForAccount(@PathVariable Long accountId){
		List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}
