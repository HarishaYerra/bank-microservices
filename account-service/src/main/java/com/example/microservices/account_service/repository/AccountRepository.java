package com.example.microservices.account_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservices.account_service.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByCustomerId(Long customerId);

}
