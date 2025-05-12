package com.shopverse.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	private String name;
	private String description;
	private double price;
	private int quantity;
	private MultipartFile imageFile;
}
