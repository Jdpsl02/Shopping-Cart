package security.proxy;

import java.util.List; 
import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import security.pojo.Product;

@FeignClient(value="product-service",url="http://localhost:8082/product")
public interface Products {

	
	@PostMapping(value = "/add",produces = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product,@RequestHeader("Authorization") String authorization);
//
//	@GetMapping(value = "/allProducts", produces = "application/json")
//	public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("Authorization") String authorization);
	
	@GetMapping(value = "/allProducts", produces = "application/json")
	public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("Authorization") String authorization);

	@GetMapping(value ="/findById/{productId}",produces = "application/json")
	public ResponseEntity<Product> getProductById(@PathVariable int productId,@RequestHeader("Authorization") String authorization);

	@GetMapping(value ="/findByName/{productName}",produces = "application/json")
	public ResponseEntity<Product> getProductByName(@PathVariable String productName,@RequestHeader("Authorization") String authorization);
	
	
	@DeleteMapping(value ="/delete/{productId}",produces = "application/json")
	public ResponseEntity<Map<String,Boolean>> deleteProductById(@PathVariable int productId,@RequestHeader("Authorization") String authorization);
	

	@GetMapping(value ="/findByCategory/{category}",produces = "application/json")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category,@RequestHeader("Authorization") String authorization);
	
	
	@GetMapping(value ="/findByType/{productType}",produces = "application/json")
	public ResponseEntity<List<Product>> getProductByType(@PathVariable String productType,@RequestHeader("Authorization") String authorization);
	
	

	
}
