package com.shopverse.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopverse.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Optional<Category> findByName(String name);

}
