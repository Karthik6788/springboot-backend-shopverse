package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Order;

public interface OrderRepository extends  JpaRepository<Order, Long>{

}
