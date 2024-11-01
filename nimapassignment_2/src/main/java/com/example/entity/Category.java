package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    
//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Product> productList = new ArrayList<>();
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
	public void setProducts(List<Product> products) {
	    this.products = products;
	    if (products != null) {
	        for (Product product : products) {
	            product.setCategory(this); // Set the category for each product
	        }
	    }
	}

    
    
}
