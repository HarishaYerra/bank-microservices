package com.example.microservices.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.transactionservice.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	List<Transaction> findByAccountId(Long accountId);
}
