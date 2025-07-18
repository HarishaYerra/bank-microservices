package com.example.microservices.customerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservices.customerservice.dto.TransactionDTO;

@FeignClient(name = "transaction-service",fallback = TransactionClientFallback.class)
public interface TransactionClient {
    @GetMapping("/transactions/account/{accountId}")
    List<TransactionDTO> getTransactionsByAccountId(@PathVariable Long accountId);
}

