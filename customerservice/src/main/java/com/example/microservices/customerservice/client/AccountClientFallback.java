package com.example.microservices.customerservice.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.microservices.customerservice.dto.AccountDTO;

@Component
public class AccountClientFallback implements AccountClient{
	 @Override
	    public AccountDTO createAccount(AccountDTO accountDTO) {
	        System.out.println("[Fallback] AccountService is down. Returning default response.");

	        AccountDTO fallback = new AccountDTO();
	        fallback.setStatus("FAILED");
	        fallback.setDescription("Fallback: Unable to create account");
	        return fallback;
	 }
	        @Override
	        public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
	            System.out.println("[Fallback] AccountService is down. Returning empty list.");
	            return Collections.emptyList();
	        }
}
