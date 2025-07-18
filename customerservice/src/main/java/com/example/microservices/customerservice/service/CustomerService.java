package com.example.microservices.customerservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservices.customerservice.client.AccountClient;
import com.example.microservices.customerservice.client.TransactionClient;
import com.example.microservices.customerservice.dto.AccountDTO;
import com.example.microservices.customerservice.dto.TransactionDTO;
import com.example.microservices.customerservice.model.Customer;
import com.example.microservices.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	
	private CustomerRepository customerRepository;
	private final AccountClient accountClient;
    private final TransactionClient transactionClient;

    
    public CustomerService(CustomerRepository customerRepository,
    		AccountClient accountClient, 
    		TransactionClient transactionClient) {
    	this.customerRepository = customerRepository;
        this.accountClient = accountClient;
        this.transactionClient = transactionClient;
    }
	
    public void logAccountCreation(AccountDTO dto) {
        AccountDTO result = accountClient.createAccount(dto);

        if ("FAILED".equals(result.getStatus())) {
            System.out.println("Account creation failed via fallback!");
        } else {
            System.out.println("Account created successfully");
        }
    }

   
	public Customer createCustomer(Customer model) {
		
		//Save Customer
		Customer customer = new Customer();
		customer.setName(model.getName());
		customer.setEmail(model.getEmail()); // 🔁 Add this
	    customer.setPhone(model.getPhone());
		
		Customer savedCustomer = customerRepository.save(customer);

	    // Automatically create an Account in AccountService
	    AccountDTO accountDTO = new AccountDTO();
	    accountDTO.setCustomerId(savedCustomer.getId());
	    accountDTO.setBalance(BigDecimal.ZERO);

	    accountClient.createAccount(accountDTO);

	    return savedCustomer;
	}
	
	
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Customer not found with id:"+id));
	}
	
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	
	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		Customer existing = getCustomerById(id);
		existing.setName(updatedCustomer.getName());
		existing.setEmail(updatedCustomer.getEmail());
		existing.setPhone(updatedCustomer.getPhone());
		return customerRepository.save(existing);
	}
	
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
	@GetMapping("/{customerId}/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsForCustomer(@PathVariable Long customerId) {
        List<AccountDTO> accounts = accountClient.getAccountsByCustomerId(customerId);

        List<TransactionDTO> allTransactions = new ArrayList<>();
        for (AccountDTO account : accounts) {
            List<TransactionDTO> transactions = transactionClient.getTransactionsByAccountId(account.getId());
            allTransactions.addAll(transactions);
        }

        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }
	
}
