package com.shopverse.backend.controller;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopverse.backend.model.Category;
import com.shopverse.backend.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class HealthController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@GetMapping("/ping")
	public ResponseEntity<String> ping(){
		
		return ResponseEntity.ok("shopverse is online");
	}

	@GetMapping()
	public void tester(@RequestParam String name) {
		Category category2=new Category();
		category2.setName(name);
		category2=categoryRepository.save(category2);
	}

}
