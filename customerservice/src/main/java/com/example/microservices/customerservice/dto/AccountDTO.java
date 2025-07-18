package com.example.microservices.customerservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDTO {
	private Long id;
    private Long customerId;
    private String accountNo;
    private BigDecimal balance;
    private String status;
    private String description;
}
