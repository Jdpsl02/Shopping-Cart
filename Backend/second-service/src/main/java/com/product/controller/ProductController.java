package com.product.controller;

import java.util.*;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {
     
	@Autowired
	private ProductService service;
	
	
	
	
	
	@GetMapping("/getproductlist")
	public List<Product> fetchProductList() {
		List<Product> products = new ArrayList<Product>();
		products = service.fetchproductList();
		return products;
		
	}
	
	
	@PostMapping("/addproduct")
	public Product saveProduct(@RequestBody  Product product) {
		return service.saveProductToDB(product);
		
	}
	
	@GetMapping("/getproductbyid/{id}")
	public Product fetchProductbyId(@PathVariable  int id) {
		return service.fetchProductToById(id).get();
		
	}
	
	
	@DeleteMapping("/deleteproductbyid/{id}")
	public String deleteProductById(@PathVariable  int id) {
		return service.deleteProductToById(id);
		
	}
	
	
	
	
}
