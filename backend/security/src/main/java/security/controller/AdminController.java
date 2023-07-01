package security.controller;

import java.util.List;  
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.Profiles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.pojo.Address;

import security.pojo.Product;

import security.proxy.Products;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private Products pr;
	
	
	


//	===================================Product Service ======================================================================================================================================

	@GetMapping(value = "/allProducts", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value = "Authorization",required = false) String authorization){
		   return pr.getAllProducts(authorization);
	}

	@PostMapping(value = "/add",produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> addProduct(@RequestBody Product product,@RequestHeader(value = "Authorization",required = false) String authorization){
		return pr.addProduct(product, authorization);
	}
	
	
	@GetMapping(value ="/findById/{productId}",produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> getProductById(@PathVariable int productId,@RequestHeader(value = "Authorization",required = false) String authorization){
	return 	pr.getProductById(productId, authorization);
	}

	@GetMapping(value ="/findByName/{productName}",produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName,@RequestHeader(value = "Authorization",required = false) String authorization){
	return pr.getProductByName(productName, authorization);
	}
	
	
	
	
	@DeleteMapping(value ="/delete/{productId}",produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String,Boolean>> deleteProductById(@PathVariable int productId,@RequestHeader(value =  "Authorization",required = false) String authorization){
	return pr.deleteProductById(productId, authorization);
}
	
	
	
	

}
