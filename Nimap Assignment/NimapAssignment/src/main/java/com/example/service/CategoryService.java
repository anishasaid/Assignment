package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import com.example.Dao.CategoryDao;
import com.example.entity.Category;

@Component
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
//	//get all category
//	public List<Category> getAllCategory(){
//		return this.categoryDao.findAll();
//	}
	
	public List<Category> getAllCategory(Integer pageNumber, Integer pageSize){
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Category> pageCategory= this.categoryDao.findAll(p);
		List<Category> allCategory = pageCategory.getContent();

//		List<Category> category = allCategory.stream().map((category)->this.modelMapper.map(category,
//				Category.class).collect(Collectors.toList());
//		
		return allCategory;
	}
	
	//get category by Id
	public Category getCategoryById(int id) {
		Category category = null;
		try {
			category = this.categoryDao.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	//add the category
	public Category addCategory(Category category) {
		
		Category result = this.categoryDao.save(category);
		return result;
	}
	
	//update category
	public void updateCategory(int id, Category category) {
		
		category.setCategoryId(id);;
		this.categoryDao.save(category);	
	}
	
	//delete category
	public void deleteCategory(int id) {
		
		this.categoryDao.deleteById(id);
	}
}
