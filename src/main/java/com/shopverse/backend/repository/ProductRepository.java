package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
