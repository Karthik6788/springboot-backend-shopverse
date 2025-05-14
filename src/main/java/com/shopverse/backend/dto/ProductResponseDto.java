package com.shopverse.backend.dto;



import lombok.Data;

@Data
public class ProductResponseDto {

	private String name;
	private String description;
	private double price;
	private int quantity;
	private String imageFileUrl;
	private CategoryResponseDto categoryResponseDto;
}
