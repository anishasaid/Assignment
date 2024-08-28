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

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//GET all the product
	//USE THIS => localhost:8080/api/products/?pageNumber=2&pageSize=1
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProduct(
			@RequestParam(value="pageNumber") Integer pageNumber,
			@RequestParam(value="pageSize") Integer pageSize) {
		
		List<Product> list =  this.productService.getAllProduct(pageNumber, pageSize);
		if(list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	//GET product by Id
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		
		Product product =  this.productService.getProductById(id);
		if(product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(product));
	}
	
	//POST - create a new product
	@PostMapping("")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		
		Product  p = null;
		try {
			p =  this.productService.addProduct(product);
			System.out.println(p);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//PUT - update product By Id
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		try {
			this.productService.updateProduct(id, product);
			return ResponseEntity.ok().body(product);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//DELETE - Delete product By Id
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
		try {
			this.productService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
