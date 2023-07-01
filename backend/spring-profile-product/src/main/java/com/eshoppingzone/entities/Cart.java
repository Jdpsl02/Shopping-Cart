package com.eshoppingzone.entities;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//store cart in db
public class Cart {
	
	
	@Id
	private int cartId;
	
	private double totalPrice;

	private List<Items> items;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public Cart(int cartId, double totalPrice, List<Items> items) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.items = items;
	}

	public Cart() {
	
	}
	
}