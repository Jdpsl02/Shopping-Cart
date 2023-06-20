package com.eshoppingzone.order.orders.repository;
import java.util.List;
import com.eshoppingzone.order.orders.Orders;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository  extends MongoRepository<Orders, Integer>{
	List<Orders> findByCustomerId(Integer customerId);
	List<Orders> findByFullName(String fullName);

	Orders findFirstByOrderByOrderIdDesc();
}