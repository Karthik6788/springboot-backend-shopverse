package com.shopverse.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import com.shopverse.backend.model.Category;

import lombok.Data;

@Data
public class ProductRequestDto {

	private String name;
	private String description;
	private double price;
	private int quantity;
	private MultipartFile imageFile;
	private CategoryRequestDto categoryRequestDto;
	
}
