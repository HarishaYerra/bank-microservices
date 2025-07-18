package com.example.microservices.customerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.microservices.customerservice.dto.AccountDTO;

@FeignClient(name = "account-service",fallback = AccountClientFallback.class)
public interface AccountClient {
	
	@PostMapping("/accounts")
	AccountDTO createAccount(@RequestBody AccountDTO accountDTO);

	
    @GetMapping("/accounts/customer/{customerId}")
    List<AccountDTO> getAccountsByCustomerId(@PathVariable Long customerId);
}

