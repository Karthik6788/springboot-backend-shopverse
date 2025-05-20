package com.shopverse.backend.model;

import java.util.List;

import com.shopverse.backend.security.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItem;
	
	
}
