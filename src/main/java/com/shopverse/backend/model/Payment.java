package com.shopverse.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.shopverse.backend.security.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	
	private double amount;
	
	private String status;
	
	private String gatewayOrderId;
	
	private String gatewayPaymentId;
	
	private String receiptNo;
	
	private String method;
	
	private LocalDateTime createdAt;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToMany(mappedBy = "payment")
	private List<PaymentTransaction> paymentTransaction;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

	

}
