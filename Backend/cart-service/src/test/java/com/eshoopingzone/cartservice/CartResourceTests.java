package com.eshoopingzone.cartservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.eshoopingzone.cartservice.entity.Cart;
import com.eshoopingzone.cartservice.entity.Items;
import com.eshoopingzone.cartservice.resource.CartResource;
import com.eshoopingzone.cartservice.service.CartService;

@SpringBootTest(classes= {CartResourceTests.class})
public class CartResourceTests {
	
	@Mock
	CartService cartService;
	
	@InjectMocks 
	CartResource cartResource;
	
	
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
	
	

}