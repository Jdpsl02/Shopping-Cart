package com.eshoppingzone.repository;

import org.springframework.data.mongodb.repository.MongoRepository; 
import org.springframework.stereotype.Repository;

import com.eshoppingzone.entities.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer> {
	Cart findByCartId(int cartId);
}
