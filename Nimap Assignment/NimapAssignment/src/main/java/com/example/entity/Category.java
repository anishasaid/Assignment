package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	private String categoryName;
	
	//1st
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Product> productList = new ArrayList<>();

//	//2nd way
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Product> productList;
//	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProductList() {
		return productList;
	}

//	public void setProductList(List<Product> productList) {
//		this.productList = productList;
//	}

	 public void setProductList(List<Product> productList) {
		 this.productList = productList;
	     for (Product product : productList) 
	     {
	    	 product.setCategory(this);
	     }
	  }
	 
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryId, String categoryName, List<Product> productList) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productList = productList;
//		for (Product product : productList) 
//		     {
//		    	 product.setCategory(this);
//		     }
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", productList=" + productList
				+ "]";
	}

	
	
	
	
	
}
