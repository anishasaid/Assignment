package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	 @Autowired
	 private CategoryService categoryService;

		@GetMapping
	    public Page<Category> getAllCategories(@RequestParam(name = "page", defaultValue = "0") int page) {
	        return categoryService.getAllCategories(page);
	    }

	    @GetMapping("/{di}")
	    public Category getCategoryById(@PathVariable("di") Long id) {
	        return categoryService.getCategoryById(id);
	    }

	    @PostMapping
	    public Category createCategory(@RequestBody Category category) {
	        return categoryService.createCategory(category);
	    }

	    @PutMapping("/{di}")
	    public Category updateCategory(@PathVariable("di") Long id, @RequestBody Category category) {
	        return categoryService.updateCategory(id, category);
	    }

	    @DeleteMapping("/{di}")
	    public void deleteCategory(@PathVariable("di") Long id) {
	        categoryService.deleteCategory(id);
	    }
}
