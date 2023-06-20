package com.eshoppingzone.productservice.resource;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshoppingzone.exception.ProductAlreadyExistsException;
import com.eshoppingzone.exception.ProductNotFoundException;
import com.eshoppingzone.productservice.entity.Product;
import com.eshoppingzone.productservice.service.ProductService;
import com.eshoppingzone.productservice.service.ProductServiceImpl;


@CrossOrigin(origins="*",maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	private ProductService productServiceImpl;
	
	Logger logger= LoggerFactory.getLogger(ProductResource.class);

	ProductResource() {
	}

	@PostMapping("/add")
	public ResponseEntity<Product> addProducts(  @RequestBody Product product) {
		
		logger.warn("product"+product);
		Product addedUser= productServiceImpl.addProduct(product);
		
		return new ResponseEntity<>(addedUser,HttpStatus.CREATED);
		
	}

	@GetMapping("/allProducts")
	public List<Product> getAllProducts() {
	 List<Product> pro=productServiceImpl.getAllProducts();
		return pro;
	}

	@GetMapping("/findById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable int productId) {
	return new ResponseEntity<>	(productServiceImpl.getProductById(productId),HttpStatus.OK);
		
	}

	@GetMapping("/findByType/{productType}")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable String productType) {
		
		return new ResponseEntity<>(productServiceImpl.getProductByType(productType),HttpStatus.OK);
	}

	@GetMapping("/findByName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
		
		return new ResponseEntity<>(productServiceImpl.getProductByName(productName),HttpStatus.OK);
	}

	@GetMapping("/findByCategory/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
		
		return new ResponseEntity<>(productServiceImpl.getProductByCategory(category),HttpStatus.OK);
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId, @Valid @RequestBody Product product) throws ProductNotFoundException {
		;
		return  ResponseEntity.ok(productServiceImpl.updateProducts(productId,product));
	}

	@DeleteMapping("/delete/{productId}")
	public Map<String,Boolean> deleteProduct(@PathVariable int productId)throws ProductNotFoundException {
		
		return productServiceImpl.deleteProductById(productId);
	}
}
