package com.example.microservices.customerservice.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.microservices.customerservice.dto.TransactionDTO;

@Component
public class TransactionClientFallback implements TransactionClient {
	@Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        System.out.println("[Fallback] TransactionService is down. Returning default response.");

//        TransactionDTO fallback = new TransactionDTO();
//        fallback.setStatus("FAILED");
//        fallback.setDescription("Fallback: Unable to record transaction");
//        return fallback;
        return Collections.emptyList();
    }
}
