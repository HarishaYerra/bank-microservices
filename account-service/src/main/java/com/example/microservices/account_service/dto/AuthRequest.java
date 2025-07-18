package com.example.microservices.account_service.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    // getters and setters
}

