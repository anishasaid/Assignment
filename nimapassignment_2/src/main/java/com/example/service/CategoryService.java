package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDao;
import com.example.entity.Category;
import com.example.entity.Product;

@Component
public class CategoryService {

	 @Autowired
	 private CategoryDao categoryDao;

	public Page<Category> getAllCategories(int page) {
		 return categoryDao.findAll(PageRequest.of(page, 2));
	 }

	 public Category getCategoryById(Long id) {
	    return categoryDao.findById(id).orElse(null);
	 }

//	 public Category createCategory(Category category) {
//	    return categoryDao.save(category);
//	 }
	 
	 public Category createCategory(Category category) {
		    // Ensure each product's category is set to the current category
		    if (category.getProducts() != null) {
		        for (Product product : category.getProducts()) {
		            product.setCategory(category);
		        }
		    }
		    return categoryDao.save(category);
		}


	 public Category updateCategory(Long id, Category category) {
	    if (categoryDao.existsById(id)) {
	        category.setId(id);
	        return categoryDao.save(category);
	        }
	    return null;
	 }

	 public void deleteCategory(Long id) {
		 categoryDao.deleteById(id);
	 }
}
