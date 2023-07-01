package com.eshoppingzone.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;  
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eshoppingzone.entities.Cart;
import com.eshoppingzone.entities.Items;
import com.eshoppingzone.entities.Product;
import com.eshoppingzone.repository.CartRepository;
import com.eshoppingzone.resource.CartResource;
import com.eshoppingzone.service.CartService;

@SpringBootTest(classes= {CartResourceTests.class})
public class CartResourceTests {
	
	@Mock
	CartService cartService;
	
	@InjectMocks 
	CartResource cartResource;
	
	 @Mock
	 private CartRepository cartRepository;

	
	@Mock
    private RestTemplate restTemplate;
	
	List<Cart> carts;
	Cart cart;
	Items item;
	List<Items> items;
	
	@Test
	@Order(1)
	public void test_getAllCart() {
		
		carts= new ArrayList<Cart>();
		items= new ArrayList<Items>();
		items.add(new Items(1, "killer", 200, 1,""));
		
		carts.add((new  Cart(1, 200,items)));
		carts.add((new  Cart(2, 200,items)));
		
		when(cartService.getallcarts()).thenReturn(carts);
		
		ResponseEntity<List<Cart>>  result=cartResource.getAllCarts();
		
		assertEquals(2,result.getBody().size() );
	}
	
	
	@Test
	@Order(2)
	public void test_getCartById() {
		
		items= new ArrayList<Items>();
		items.add(new Items(1, "killer", 200, 1,""));
		

		
		cart=new  Cart(1, 200,items);
		
		int cartId=1;
	
		
		when(cartService.getcartById(cartId)).thenReturn(cart);
		
		
		ResponseEntity<Cart> result=cartResource.getCartById(cartId);
		

		assertEquals(cartId, result.getBody().getCartId());
	}
	
	  @Test
	  @Order(3)
	    public void testAddToCart_ExistingCartNewProduct() {
	        int cartId = 1;
	        int productId = 2;
	        Product product = new Product();
	        Cart existingCart = new Cart();
	        existingCart.setCartId(cartId);
	        existingCart.setItems(new ArrayList<>());

	        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Product.class))).thenReturn(product);
	        Mockito.when(cartRepository.existsById(cartId)).thenReturn(true);
	        Mockito.when(cartRepository.findById(cartId)).thenReturn(Optional.of(existingCart));
	        Mockito.when(cartService.addCart(existingCart)).thenReturn(existingCart);

	        ResponseEntity<Cart> response = cartResource.addCart(cartId, productId);

	        assertEquals(existingCart, response.getBody());
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(1, existingCart.getItems().size()); // Verify new item is added
	        assertEquals(productId, existingCart.getItems().get(0).getProductId()); // Verify correct product is added
	    }

	
	
	@Test
	@Order(4)
	public void test_updateCart() {
			
		items= new ArrayList<Items>();
		items.add(new Items(1, "killer", 200, 1,""));
		

		
		cart=new  Cart(1, 200,items);
		int cartId=1;
	
		
		when(cartService.getcartById(cartId)).thenReturn(cart);
		when(cartService.updateCart(cartId,cart)).thenReturn(cart);
		
		
		ResponseEntity<Cart> result= cartResource.updateCart(cartId,cart);
		
	
		
		assertEquals(cartId, result.getBody().getCartId());
	}
	
	
	 @Test
	 @Order(5)
	    public void testDeleteCart() {
	        int cartId = 1;

	        cartResource.deleteCart(cartId);

	        Mockito.verify(cartService, Mockito.times(1)).deleteCartById(cartId);
	    }
	 
	 
	 
	
	

}