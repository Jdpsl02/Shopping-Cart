package com.eshoppingzone.service;

import java.util.List; 
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eshoppingzone.entities.Product;

//interface for service layer
@Service
public interface ProductService {

	public Product addProduct(Product product);

	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public Product getProductByName(String productName);

	public Product updateProducts(int productId,Product product);

	public Map<String,Boolean> deleteProductById(int productId);

	public List<Product> getProductByCategory(String category);

	public List<Product> getProductByType(String productType);
}
