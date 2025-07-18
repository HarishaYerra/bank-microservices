package com.example.microservices.account_service.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.microservices.account_service.model.Account;


public interface AccountService {

	List<Account> getAccountsByCustomerId(Long customerId);
	
	Account createAccount(Account dto);

	List<Account> getAllAccounts();
	

	BigDecimal getBalance(Long accountId);
	
	void deposit(Long accountId, BigDecimal amount);

    void withdraw(Long accountId, BigDecimal amount);

    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
