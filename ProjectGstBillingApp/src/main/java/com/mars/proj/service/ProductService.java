package com.mars.proj.service;

import java.util.List;

import com.mars.proj.entity.Product;

public interface ProductService {

public List<Product> findAll();
	
	public Product findById(int theId);
	
	public void save(Product theProduct);
	
	public void deleteById(int theId);

	public List<Product> findByCode(String theCode);
	
	public List<Product> findByName(String theName);
	
}
