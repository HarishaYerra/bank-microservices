package com.example.microservices.customerservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDTO {
	private Long id;
    private Long accountId;
    private String type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;
    private String status;
}
