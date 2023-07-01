package com.eshoppingzone.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.eshoppingzone.entities.Cart;

import com.eshoppingzone.exception.CartAlreadyExistsException;
import com.eshoppingzone.exception.CartNotFoundException;
import com.eshoppingzone.repository.CartRepository;


@Service
public class CartServiceImpl implements CartService {
	
	Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private CartRepository cartRepository;
	

	
	
	@Override
	public Cart getcartById(int cartId) throws CartNotFoundException {
			
		return cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException());
	}

	@Override
	public Cart updateCart(int cartId,Cart cart) throws CartNotFoundException {
		
		Cart updatedCart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException());
		
		updatedCart.setCartId( cart.getCartId());
		updatedCart.setItems( cart.getItems());
		updatedCart.setTotalPrice( cart.getTotalPrice());
		
		cartRepository.save(updatedCart);
		
		return updatedCart;
		
	}

	@Override
	public List<Cart> getallcarts() {
		
		return cartRepository.findAll();
	}

	@Override
	public double cartTotal(Cart cart) {
		
		return cart.getTotalPrice();
	}

	@Override
	public Cart addCart(Cart cart) throws CartAlreadyExistsException {

		return cartRepository.save(cart);
	}

	@Override
	public void deleteCartById(int cartId) {
		cartRepository.deleteById(cartId);
		
	}

}