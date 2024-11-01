package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDao;
import com.example.entity.Product;

@Component
public class ProductService {

	 @Autowired
	 private ProductDao productDao;

	public Page<Product> getAllProducts(int page) {
		 return productDao.findAll(PageRequest.of(page, 10));
	 }

	 public Product getProductById(Long id) {
		 return productDao.findById(id).orElse(null);
	 }

	 public Product createProduct(Product product) {
	     return productDao.save(product);
	 }

	 public Product updateProduct(Long id, Product product) {
		 if (productDao.existsById(id)) {
			 product.setId(id);
			 return productDao.save(product);
		 }
		 return null;
	 }

	 public void deleteProduct(Long id) {
		 productDao.deleteById(id);
	 }
}
