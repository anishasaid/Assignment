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

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	    @Autowired
	    private ProductService productService;

		@GetMapping
	    public Page<Product> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
	        return productService.getAllProducts(page);
	    }

	    @GetMapping("/{di}")
	    public Product getProductById(@PathVariable("di") Long id) {
	        return productService.getProductById(id);
	    }

	    @PostMapping
	    public Product createProduct(@RequestBody Product product) {
	        return productService.createProduct(product);
	    }

	    @PutMapping("/{di}")
	    public Product updateProduct(@PathVariable("di") Long id, @RequestBody Product product) {
	        return productService.updateProduct(id, product);
	    }

	    @DeleteMapping("/{di}")
	    public void deleteProduct(@PathVariable("di") Long id) {
	        productService.deleteProduct(id);
	    }
}
