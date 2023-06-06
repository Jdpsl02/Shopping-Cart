package com.product.service;

import java.util.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.product.model.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	

	public List<Product> fetchproductList(){
		return repo.findAll();
	}
	
	
	public Product saveProductToDB(Product product) {
		return repo.save(product);
		
	}
	
	public Optional<Product> fetchProductToById(int id) {
		return repo.findById(id);
		
	}
	
	
	public String deleteProductToById(int id) {
		
		String result;
	try {
		repo.deleteById(id);;
		result = "Product deleted successfully";
	}catch(Exception e) {
		result = "product with id is not deleted";
	}
		
		return result;
	
	
	
}
	
}
