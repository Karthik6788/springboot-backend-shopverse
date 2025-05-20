package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
