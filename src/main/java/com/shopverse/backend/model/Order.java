package com.shopverse.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.shopverse.backend.model.enumclasses.Status;
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
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	private int totalAmount;
	
	private Status status;
	
	private LocalDateTime created_at;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItem;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToOne(mappedBy = "order")
	private Payment payment;
	
	
}
