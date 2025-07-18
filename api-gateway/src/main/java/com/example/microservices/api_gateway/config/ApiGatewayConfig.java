package com.example.microservices.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                // Account Service
                .route("account-service", r -> r.path("/account/**")
                        .uri("lb://account-service"))

                // Customer Service
                .route("customer-service", r -> r.path("/customer/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://customerservice"))

                // Transaction Service
                .route("transaction-service", r -> r.path("/transaction/**")
                        .uri("lb://transactionservice"))

                .build();
    }
}
