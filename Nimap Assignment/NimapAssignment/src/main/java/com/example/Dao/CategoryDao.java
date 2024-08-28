package com.example.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	public Category findById(int id);
}
