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
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Cart")
//store cart in db
public class Cart {
	

	
	@Id
	private int cartId;
	
	private double totalPrice;

	private List<Items> items;

	
	
}