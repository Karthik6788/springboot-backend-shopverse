package com.shopverse.backend.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRole(String Role);

}
