package com.shopverse.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/ping")
	public ResponseEntity<String> adminPing(){
		return ResponseEntity.ok("Admin Page is live");
	}

}
