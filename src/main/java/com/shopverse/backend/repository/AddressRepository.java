package com.shopverse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
