package com.shopverse.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PaymentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentTransactionId;
	
	private String type;

	private String message;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
}
