package com.shopverse.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopverse.backend.dto.ProductDTO;
import com.shopverse.backend.dto.ProductResponse;
import com.shopverse.backend.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductControl {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping()
	public ResponseEntity<?> saveProduct(@ModelAttribute ProductDTO productDto){
		
		return productService.saveProduct(productDto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable int id){
		return productService.getProductById(id);
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProductById(@PathVariable int id,@ModelAttribute ProductDTO productDto) throws IOException{
		return productService.updateProductById(id,productDto);
	} 
	

}
