package com.example.microservices.account_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositRequest {
	private BigDecimal amount;
}
