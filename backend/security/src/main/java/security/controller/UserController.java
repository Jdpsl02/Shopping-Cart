package security.controller;
 
import java.util.*;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.Profiles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.client.RestTemplate;

import security.payload.request.SignUpRequest;
import security.pojo.Address;

import security.pojo.Items;

import security.pojo.Product;
import security.pojo.User;
import security.pojo.UserProfile;

import security.proxy.Products;

import security.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private Products pr;
	

	@Autowired
	RestTemplate restTemplate;
	

	

    @GetMapping(value = "/allProducts", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value =  "Authorization",required = false) String authorization){
		   return pr.getAllProducts(authorization);
	}
	
	@GetMapping(value ="/findByCategory/{category}",produces = "application/json")
      @PreAuthorize("hasRole('USER')")	
      public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category,@RequestHeader(value =  "Authorization",required = false) String authorization){
		
		return pr.getProductByCategory(category, authorization);
	}
	
	
	@GetMapping(value ="/findByType/{productType}",produces = "application/json")
     @PreAuthorize("hasRole('USER')")	
     public ResponseEntity<List<Product>> getProductByType(@PathVariable String productType,@RequestHeader(value =  "Authorization",required = false) String authorization){
	 return	pr.getProductByType(productType, authorization);
	}
	
	@GetMapping(value ="/findByName/{productName}",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName,@RequestHeader(value =  "Authorization",required = false) String authorization){
	return pr.getProductByName(productName, authorization);
	}


}
