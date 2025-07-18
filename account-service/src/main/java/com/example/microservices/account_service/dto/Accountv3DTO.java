package com.example.microservices.account_service.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Accountv3DTO {
    private Long id;
    private Long customerId;
    private String accountNo;
    private BigDecimal balance;
    private String accountType;
}
