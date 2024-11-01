package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

}
