package com.eshoppingzone.orders.repository;

import java.util.List; 

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eshoppingzone.orders.model.Address;

@Repository
public interface AddressRepository extends  MongoRepository<Address, Integer> {
	List<Address> findByCustomerId(int customerId);
	List<Address> findByFullName(String fullName);
}