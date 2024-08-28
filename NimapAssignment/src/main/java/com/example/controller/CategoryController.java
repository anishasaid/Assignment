package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/categories/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	//GET all the categories
	//localhost:8080/api/categories/?pageNumber=3&pageSize=1
	@GetMapping("")
	public ResponseEntity<List<Category>> getAllCategory(
			@RequestParam(value="pageNumber") Integer pageNumber, 
			@RequestParam(value="pageSize") Integer pageSize) {
		
		List<Category> list = this.categoryService.getAllCategory(pageNumber, pageSize);
		if(list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	//GET category by Id
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
		
		Category category = this.categoryService.getCategoryById(id);
		if(category == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(category));
	}
	
	//POST - create a new category
	@PostMapping("")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		
		Category c = null;
		try {
			c = this.categoryService.addCategory(category);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//PUT - update category by Id
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
		
		try {
			this.categoryService.updateCategory(id, category);
			return ResponseEntity.ok().body(category);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//DELeTE - delete category By Id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
		try {
			this.categoryService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
