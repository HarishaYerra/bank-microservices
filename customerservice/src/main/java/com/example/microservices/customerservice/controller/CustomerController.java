package com.example.microservices.customerservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.customerservice.client.AccountClient;
import com.example.microservices.customerservice.client.TransactionClient;
import com.example.microservices.customerservice.dto.AccountDTO;
import com.example.microservices.customerservice.dto.TransactionDTO;
import com.example.microservices.customerservice.model.Customer;
import com.example.microservices.customerservice.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
  
	
	private final CustomerService customerService;
    private final AccountClient accountClient;
    private final TransactionClient transactionClient;

    
    public CustomerController(CustomerService customerService,
                              AccountClient accountClient,
                              TransactionClient transactionClient) {
        this.customerService = customerService;
        this.accountClient = accountClient;
        this.transactionClient = transactionClient;
    }
	@PostMapping
	public Customer create(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/{id}")
	public Customer getById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}
	
	@GetMapping
	public List<Customer> getAll(){
		return customerService.getAllCustomers();
	}
	
	@PutMapping("/{id}")
	public Customer update(@PathVariable Long id,@RequestBody Customer customer) {
		return customerService.updateCustomer(id,customer);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
	
	 @GetMapping("/{customerId}/transactions")
	    public List<TransactionDTO> getAllTransactionsForCustomer(@PathVariable Long customerId) {
	        List<AccountDTO> accounts = accountClient.getAccountsByCustomerId(customerId);

	        List<TransactionDTO> allTransactions = new ArrayList<>();
	        for (AccountDTO account : accounts) {
	            List<TransactionDTO> transactions = transactionClient.getTransactionsByAccountId(account.getId());
	            allTransactions.addAll(transactions);
	        }

	        return allTransactions;
	    }
}


