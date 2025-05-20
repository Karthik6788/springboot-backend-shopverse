package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
