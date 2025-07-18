package com.example.microservices.account_service.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.account_service.dto.DepositRequest;
import com.example.microservices.account_service.dto.TransferRequest;
import com.example.microservices.account_service.model.Account;
import com.example.microservices.account_service.service.AccountService;

@RestController
@RequestMapping("/api/accounts/param")
public class AccountControllerParamVersion {
	
    private final AccountService accountService;
    
    @Autowired
    public AccountControllerParamVersion(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @GetMapping(params = "version=1")
    public String getAccountV1(@RequestParam Long id) {
        return "Account V1 details for ID: " + id;
    }
    @GetMapping(params = "version=2")
    public String getAccountV2(@RequestParam Long id) {
        return "Account V2 details with extra fields for ID: " + id;
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
    
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(@PathVariable Long customerId) {
//        //List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customerId);
//        List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customerId)
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(accounts);    
//     }
    
    private Account convertToDTO(Account account) {
        Account dto = new Account();
        dto.setId(account.getId());
        dto.setCustomerId(account.getCustomerId());
        dto.setAccountNo(account.getAccountNo());
        dto.setBalance(account.getBalance());
        return dto;
    }

}


