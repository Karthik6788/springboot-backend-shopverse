package com.shopverse.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopverse.backend.dto.CategoryRequestDto;
import com.shopverse.backend.dto.CategoryResponseDto;
import com.shopverse.backend.dto.ProductResponseDto;
import com.shopverse.backend.model.Category;
import com.shopverse.backend.model.Product;
import com.shopverse.backend.repository.CategoryRepository;
import com.shopverse.backend.repository.ProductRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<String> addCategory(CategoryRequestDto categoryDto){
		Category category=new Category();
		category.setName(categoryDto.getName());
		categoryRepository.save(category);
		
		return ResponseEntity.ok("Category Saved Succefully");
	}
	
	public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
		List<Category> categories=categoryRepository.findAll();
		List<CategoryResponseDto> categoryResponseDtos=new ArrayList<>();
		for(Category category:categories) {
			CategoryResponseDto dto=new CategoryResponseDto();
			dto.setName(category.getName());
			categoryResponseDtos.add(dto);
			
		}
		return ResponseEntity.ok(categoryResponseDtos);
	}

}
