package com.shopverse.backend.model;

import java.util.List;

import com.shopverse.backend.security.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	
	private String line1;
	private String city;
	private String state;
	private String pincode;
	private boolean isDefault;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "address")
	private List<Order> order;
}
