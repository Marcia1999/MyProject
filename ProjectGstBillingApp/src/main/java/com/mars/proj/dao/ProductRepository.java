package com.mars.proj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mars.proj.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


	// add a method to sort by last name
	public List<Product> findAllByOrderByIdAsc();
    public List<Product> findByCode(String theCode);
    public List<Product> findByName(String theName);
}
