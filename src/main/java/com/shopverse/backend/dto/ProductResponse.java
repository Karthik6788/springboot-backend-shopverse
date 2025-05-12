package com.shopverse.backend.dto;

import lombok.Data;

@Data
public class ProductResponse {

	private String name;
	private String description;
	private double price;
	private int quantity;
	private String imageFileUrl;
}
