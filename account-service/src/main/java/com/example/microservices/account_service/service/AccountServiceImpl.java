package com.example.microservices.account_service.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.account_service.client.TransactionServiceClient;
import com.example.microservices.account_service.dto.TransactionDTO;
import com.example.microservices.account_service.exception.AccountNotFoundException;
import com.example.microservices.account_service.exception.InsufficientFundsException;
import com.example.microservices.account_service.model.Account;
import com.example.microservices.account_service.repository.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
public class AccountServiceImpl implements AccountService {

	
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionServiceClient transactionServiceClient;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString();
    }
    
    public void logTransaction(TransactionDTO dto) {
        TransactionDTO result = transactionServiceClient.recordTransaction(dto);

        if ("FAILED".equals(result.getStatus())) {
            // Optionally handle fallback case
            System.out.println("Transaction recording failed via fallback!");
        } else {
            System.out.println("Transaction recorded successfully");
        }
    }
    
    @Override
    public Account createAccount(Account dto) {
        //accountRepository.save(account);
    	Account account = new Account();
        account.setCustomerId(dto.getCustomerId());
        account.setBalance(dto.getBalance());
        account.setAccountNo(generateAccountNumber()); // Or however you want to generate it
        return accountRepository.save(account);
    }

    
    @Override
    public BigDecimal getBalance(Long accountId) {
        Account account = getAccountById(accountId);
        return account.getBalance();
    }

    @Override
    @Transactional
    public void deposit(Long accountId, BigDecimal amount) {
        validateAmount(amount);

        Account account = getAccountById(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        
        //Record-transaction
        TransactionDTO txn = new TransactionDTO();
        txn.setAccountId(accountId);
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setDescription("Deposit to account");
        
        transactionServiceClient.recordTransaction(txn);
        // Mock transaction log
        log.info("Transaction [DEPOSIT] for AccountId {} Amount {}", accountId, amount);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        validateAmount(amount);

        Account account = getAccountById(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        
      //Record-transaction
        TransactionDTO txn = new TransactionDTO();
        txn.setAccountId(accountId);
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setDescription("Deposit to account");
        
        transactionServiceClient.recordTransaction(txn);
        
        log.info("Transaction [WITHDRAW] for AccountId {} Amount {}", accountId, amount);
    }

    @Override
    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        withdraw(fromAccountId, amount);
        deposit(toAccountId, amount);

        log.info("Transaction [TRANSFER] from {} to {} Amount {}", fromAccountId, toAccountId, amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));
    }
    
    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
    
}



