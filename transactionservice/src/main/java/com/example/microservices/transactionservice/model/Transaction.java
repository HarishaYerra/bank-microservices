package com.example.microservices.transactionservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	
	@Column(name="account_id",nullable=false)	
	private Long accountId;
	
	@Column(name="type", nullable =false)
	private String type;
	
	@Column(name="amount",nullable=false)
	private BigDecimal amount;
	
	@Column(name="timestamp",nullable=false)
	private LocalDateTime timestamp;
	
	@Column(name="description")
	private String description;
}
