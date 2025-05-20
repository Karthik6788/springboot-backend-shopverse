package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
