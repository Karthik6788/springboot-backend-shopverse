package com.shopverse.backend.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
	
	Optional<User> findByUserName(String userName);

}
