package com.example.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	public Product findById(int id);
}
