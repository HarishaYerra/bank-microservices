package com.example.microservices.account_service.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.account_service.dto.DepositRequest;
import com.example.microservices.account_service.dto.TransferRequest;
import com.example.microservices.account_service.model.Account;
import com.example.microservices.account_service.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerv1 {
	
    private final AccountService accountService;
    
    @Autowired
    public AccountControllerv1(AccountService accountService) {
        this.accountService = accountService;
    }

    
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account dto) {
        Account created = accountService.createAccount(dto);
        return ResponseEntity.ok(convertToDTO(created));
    }
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestBody DepositRequest request) {
        accountService.deposit(id, request.getAmount());
        return "Deposit successful";
    }

    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestBody DepositRequest request) {
        accountService.withdraw(id, request.getAmount());
        return "Withdrawal successful";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        accountService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return "Transfer successful";
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        //List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customerId);
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accounts);    
     }
    
    private Account convertToDTO(Account account) {
        Account dto = new Account();
        dto.setId(account.getId());
        dto.setCustomerId(account.getCustomerId());
        dto.setAccountNo(account.getAccountNo());
        dto.setBalance(account.getBalance());
        return dto;
    }

}


