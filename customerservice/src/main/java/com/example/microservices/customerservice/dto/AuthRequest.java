package com.example.microservices.customerservice.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    // getters and setters
}
