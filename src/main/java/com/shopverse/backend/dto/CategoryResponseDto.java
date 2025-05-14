package com.shopverse.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryResponseDto {
	
	private String name;
	private List<ProductResponseDto> productResponseDtos;

}
