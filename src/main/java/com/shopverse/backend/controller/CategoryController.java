package com.shopverse.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopverse.backend.dto.*;
import com.shopverse.backend.service.CategoryService;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired CategoryService categoryService;
	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> getAllCategory() {
		return categoryService.getAllCategory();
	}

}
