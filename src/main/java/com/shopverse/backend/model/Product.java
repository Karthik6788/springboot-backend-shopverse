package com.shopverse.backend.model;

import java.util.List;

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
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private int stock;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItem;
	
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderitem;
	
	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImage;
	

}
