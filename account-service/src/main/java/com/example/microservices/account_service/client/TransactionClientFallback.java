package com.example.microservices.account_service.client;

import org.springframework.stereotype.Component;

import com.example.microservices.account_service.dto.TransactionDTO;

@Component
public class TransactionClientFallback implements TransactionServiceClient{

	@Override
	public TransactionDTO recordTransaction(TransactionDTO transactionDTO) {
		//Log the failure
		System.out.println("[Fallback] TransactionService is down. Returning default response");
		
		//Return a default/failure TransactionDTO
		TransactionDTO fallback = new TransactionDTO();
		fallback.setStatus("FAILED");
		fallback.setDescription("Fallback: Unable to record transaction");
		return fallback;
	}
}
