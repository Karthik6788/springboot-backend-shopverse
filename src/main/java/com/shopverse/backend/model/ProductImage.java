package com.shopverse.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProductImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productImageId;
	
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
	
	
	
	

}
