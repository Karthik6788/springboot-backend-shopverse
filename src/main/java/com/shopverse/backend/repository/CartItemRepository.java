package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
