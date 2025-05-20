package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
