package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.Dao.ProductDao;
import com.example.entity.Category;
import com.example.entity.Product;

@Component
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
//	//get all product
//	public List<Product> getAllProduct(){
//		return this.productDao.findAll();
//	}
	
	public List<Product> getAllProduct(Integer pageNumber, Integer pageSize){
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Product> pageProduct = this.productDao.findAll(p);
		List<Product> allCategory = pageProduct.getContent();

		return allCategory;
	}
	
	//get single product By id
	public Product getProductById(int id) {
		Product product = null;
		try {
			product = this.productDao.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	// add new product
	public Product addProduct(Product product) {
		Product result = this.productDao.save(product);
		return result;
	}
	
	//upadate product
	public void updateProduct(int id, Product product) {
		product.setProductId(id);
		this.productDao.save(product);
	}
	
	//delete Product
	public void deleteProduct(int id) {
		this.productDao.deleteById(id);
	}
}
