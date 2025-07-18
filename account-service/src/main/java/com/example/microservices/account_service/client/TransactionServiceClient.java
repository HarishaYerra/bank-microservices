package com.example.microservices.account_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.microservices.account_service.dto.TransactionDTO;


@FeignClient(name = "transactionservice")
public interface TransactionServiceClient {
	@PostMapping("/transactions")
	TransactionDTO recordTransaction(@RequestBody TransactionDTO transactionDTO);
}
