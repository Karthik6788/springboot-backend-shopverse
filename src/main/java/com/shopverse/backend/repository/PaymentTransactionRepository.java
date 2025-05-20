package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.PaymentTransaction;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction,Long>{

}
