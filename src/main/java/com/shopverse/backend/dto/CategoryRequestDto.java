package com.shopverse.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryRequestDto {
	
	private String name;
	private List<ProductRequestDto> productRequestDtos;

}
