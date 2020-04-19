package com.mars.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.proj.dao.ProductRepository;
import com.mars.proj.entity.Product;
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		productRepository = theProductRepository;
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Product findById(int theId) {
		Optional<Product> result = productRepository.findById(theId);
		
		Product theProduct = null;
		
		if (result.isPresent()) {
			theProduct = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find product id - " + theId);
		}
		
		return theProduct;
	}
   
	@Override
	public List<Product> findByCode(String theCode) {
		List<Product> result = productRepository.findByCode(theCode);
		return productRepository.findByCode(theCode);
	}
	
	@Override
	public void save(Product theProduct) {
		productRepository.save(theProduct);
	}

	@Override
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
	}

	@Override
	public List<Product> findByName(String theName) {
		List<Product> result = productRepository.findByName(theName);
		return productRepository.findByName(theName);
	}
}
