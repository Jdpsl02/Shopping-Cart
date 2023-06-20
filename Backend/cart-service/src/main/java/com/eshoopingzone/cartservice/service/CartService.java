package com.eshoopingzone.cartservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshoopingzone.cartservice.entity.Cart;

@Service
public interface CartService  {
	
	Cart getcartById(int cartId);

	Cart updateCart(int cartId,Cart cart);

	List<Cart> getallcarts();

	double cartTotal(Cart cart);

	Cart addCart(Cart cart);
	void deleteCartById(int cartId);
}