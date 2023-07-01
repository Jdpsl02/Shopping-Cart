package com.eshoppingzone.orders.model;
import java.util.Objects; 

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Items {
	
	private int productId;
	@NotEmpty
	private String productName;
	@NotEmpty
	@Min(0)
	private double price;
	@Min(1)
	private int quantity;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public Items(int productId, @NotEmpty String productName, @NotEmpty @Min(0) double price, @Min(1) int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Items() {
		
	}
	
	
	
	

	
}