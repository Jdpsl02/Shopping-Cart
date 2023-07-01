package com.eshoppingzone.resource;

import java.util.ArrayList;  
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.client.RestTemplate;

import com.eshoppingzone.entities.Cart;
import com.eshoppingzone.entities.Items;
import com.eshoppingzone.entities.Product;
import com.eshoppingzone.exception.CartNotFoundException;
import com.eshoppingzone.repository.CartRepository;
import com.eshoppingzone.service.CartService;
import com.eshoppingzone.service.CartServiceImpl;




@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartResource {
	
	
	@Autowired
	private CartService cartServiceImpl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CartRepository cartRepository;

	Logger logger= LoggerFactory.getLogger(CartResource.class);
	
	public CartResource() {
	}
	
	//list all cart
	@GetMapping("/listAll")
	public ResponseEntity<List<Cart>> getAllCarts() {
		return ResponseEntity.ok(cartServiceImpl.getallcarts());

	}
	
	//add product to cart using productId and cart Id
	@PostMapping("/addToCart/{cartId}/{productId}")
	public ResponseEntity<Cart> addCart( @PathVariable(value="cartId")  int cartId ,@PathVariable(value="productId")  int productId) {
Product product=restTemplate.getForObject("http://localhost:8082/product/findById/"+productId,Product.class);
		
		logger.info(""+product);
		if(cartRepository.existsById(cartId)) {
		Cart oldCart= cartRepository.findById(cartId).orElseThrow( ()-> new CartNotFoundException("cart id not found"));
		
		
		List<Integer> idList = new ArrayList<>();
		List<Items> oldItem3 =oldCart.getItems();
		
		for(Items i : oldItem3) {
			idList.add(i.getProductId());
		}
		
		if(idList.contains(product.getProductId())) {
			logger.info("in if method");
			List<Items> oldItem2= oldCart.getItems();
			

			for (Items i : oldItem2) {
				
				if(i.getProductId()==productId) {
					i.setQuantity(i.getQuantity()+1);
				}
			}
			
			double price =0;
			
			for (Items i : oldItem2) {
				
				price = price+ i.getPrice()*i.getQuantity();
			}
			oldCart.setTotalPrice(price);
			
			return new ResponseEntity<>(cartServiceImpl.addCart(oldCart),HttpStatus.CREATED);
			
			
		}else {
		
		
		Items items= new Items();
		items.setProductId(productId);
		items.setPrice(product.getPrice());
		items.setProductName(product.getProductName());
		items.setQuantity(1);
		items.setImage(product.getImage());
		
		
		List<Items> oldItems =oldCart.getItems();
			oldItems.add(items);
			oldCart.setItems(oldItems);
		
			double price =0;
			
			for (Items i : oldItems) {
				
				price = price+ i.getPrice()*i.getQuantity();
			}
			oldCart.setTotalPrice(price);
			
			return new ResponseEntity<>(cartServiceImpl.addCart(oldCart),HttpStatus.CREATED);
		}
		}else {
		
		Cart cart =  new Cart();
		cart.setCartId(cartId);
		Items items= new Items();
		items.setProductId(productId);
		items.setPrice(product.getPrice());
		items.setProductName(product.getProductName());
		items.setQuantity(1);
		items.setImage(product.getImage());
		List<Items> list= new ArrayList<>();
		list.add(items);
	
		
		cart.setItems(list);
		double price =0;
		
		for (Items i : list) {
			
			price = price+ i.getPrice()*i.getQuantity();
		}
		cart.setTotalPrice(price);
		
		
		
		return new ResponseEntity<>(cartRepository.save(cart),HttpStatus.CREATED);
		}


	
	}
	
	//get cart by cartId
	@GetMapping("/findCart/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
		return new ResponseEntity<>(cartServiceImpl.getcartById(cartId),HttpStatus.OK);

	}
	
	//update cart by cartId
	@PutMapping("/updateCart/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable int cartId ,@Valid @RequestBody Cart cart) {
		
		
		return ResponseEntity.ok(cartServiceImpl.updateCart(cartId, cart));
		

	}
	
	//delete cart by cartId
	@DeleteMapping("/delete/{cartId}")
	public void deleteCart(@PathVariable int cartId) {
		cartServiceImpl.deleteCartById(cartId);
	}
	
	//remove item from cart using productId and cartId
	@PutMapping("/delete/item/{productId}/{cartId}")
	public Cart deleteCartItem(@PathVariable int productId ,@PathVariable int cartId) {
		
		Product product=restTemplate.getForObject("http://localhost:8082/product/findById/"+productId,Product.class);

		Cart cart2=cartServiceImpl.getcartById(cartId);
		List<Items> list= new ArrayList<>();
		list=cart2.getItems();
		System.out.println(list);
		
		list.removeIf(i->(i.getProductId()==productId));
		cart2.setItems(list);
		
	
		double price =0;
		
		for (Items i : list) {
			
			price = price+ i.getPrice()*i.getQuantity();
		}
		cart2.setTotalPrice(price);
	
		return 	cartServiceImpl.updateCart(cart2.getCartId(), cart2);
		
	}
	
	// increase product count in cart using product and cart Id
	@PutMapping("/increaseQuant/{productId}/{cartId}")
	public Cart increaseItem(@PathVariable int productId,@PathVariable int cartId) {
		
		Cart cart =cartServiceImpl.getcartById(cartId);
		List<Items> oldItem3 =cart.getItems();
		
			for (Items i : oldItem3) {
				
				if(i.getProductId()==productId) {
					i.setQuantity(i.getQuantity()+1);
				}
			}
			
			double price =0;
			
			for (Items i : oldItem3) {
				
				price = price+ i.getPrice()*i.getQuantity();
			}
			cart.setTotalPrice(price);
			
			return cartServiceImpl.updateCart(productId, cart);
			
			
	}
	
	
	//decrease item quantity in cart from cartId and  productId
	@PutMapping("/decreaseQuant/{productId}/{cartId}")
	public Cart decreaseItem(@PathVariable int productId,@PathVariable int cartId) {
		
		
		Cart cart =cartServiceImpl.getcartById(cartId);
		
	
		List<Items> oldItem3 =cart.getItems();

			for (Items i : oldItem3) {
				
				if(i.getProductId()==productId) {
					i.setQuantity(i.getQuantity()-1);
				}
			}
			
			double price =0;
			
			for (Items i : oldItem3) {
				
				price = price+ i.getPrice()*i.getQuantity();
			}
			cart.setTotalPrice(price);
			
			return cartServiceImpl.updateCart(productId, cart);
			
			
	}
	

	
	
	}
	
	

