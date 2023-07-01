package com.eshoppingzone.orders.service;

import java.text.DateFormat;   
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.eshoppingzone.orders.model.Address;
import com.eshoppingzone.orders.model.Cart;
import com.eshoppingzone.orders.model.Orders;
import com.eshoppingzone.orders.model.Product;
import com.eshoppingzone.orders.model.Items;
import com.eshoppingzone.orders.repository.AddressRepository;
import com.eshoppingzone.orders.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;



@Service
public class OrderServiceImpl implements OrderService {

	Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;
	

	
	
	//listing all orders 
	@Override
	public List<Orders> getAllOrders() {
		
		return orderRepository.findAll();
	}
	
	//place order using customer fullName and cartId
	@Override
	public void placeOrder(Cart cart,String mode,String fullName) {
		
		Orders order=new Orders();
		order.setOrderId(seqService.getSequenceNum(Orders.SEQUENCE_NAME));
	
		LocalDate today = LocalDate.now();
		
		Items i = new Items();

         Product pro  = new Product();
         
         
	
		order.setOrderDate(today);
		order.setAmmountPaid(cart.getTotalPrice());
		order.setFullName(fullName);
		order.setModeOfPayment(mode);
		order.setQuantity(cart.getItems().size());
		order.setOrderStatus("confirmed");
		Address address=  addressRepository.findByFullName(fullName).get(0);
		order.setCustomerId(address.getCustomerId());
		order.setAddress(address); 
		
		
		orderRepository.save(order);
		 logger.info(("order is placed with"+order.getOrderId()));
		 

		
	}
	
	//change order status of existing order
	@Override
	public String changeStatus(String status, int orderId) {
		
	Orders order =orderRepository.findById(orderId).orElseThrow();
	order.setOrderStatus(status);
	
	return "Order Status is changed to "+status;
		
		
	}
	
	//delete order by orderId
	@Override
	public void deleteOrder(int orderId) {
		orderRepository.deleteById(orderId);
		
	}
	
	//get order by  customer Id
	@Override
	public List<Orders> getOrderByCustomerId(int customerId) {
		
		return orderRepository.findByCustomerId(customerId);
	}
	
	//store  address  for each order
	@Override
	public void storeAddress(Address address) {
		address.setCustomerId(seqService.getSequenceNum(Address.SEQUENCE_NAME));
		
		addressRepository.save(address);
		
	}
	
	//get address by customer Id
	@Override
	public List<Address> getAddressByCustomerId(int customerId) {
		
		
		return addressRepository.findByCustomerId(customerId);
	}
	
	//get All address
	@Override
	public List<Address> getAllAddress() {
	
		return addressRepository.findAll();
	}
	
	// get Order by orderId
	@Override
	public Orders getOrderById(int orderId) {
		
		return orderRepository.findById(orderId).orElseThrow();
	}

	
	//online payment  to place order
	@Override
	public String onlinePayment(Cart cart) throws RazorpayException {
		
		double amt= cart.getTotalPrice();
		System.out.println(amt);
		
		 RazorpayClient client =  new RazorpayClient("rzp_test_0LLxBP5PFdpRSP","z4NhG0iS2PGwHmyhRjXO3XGo" );
		 JSONObject options = new JSONObject();
		 options.put("amount", amt*100);
		 options.put("currency", "INR");
		 options.put("receipt", "txn_123456");
		 Order order = client.Orders.create(options);
		 System.out.println(order);
		 return order.toString();
	}
	
	//find order by customer fullName
	@Override
	public List<Orders> findOrderByFullName(String fullName) {
		
		return orderRepository.findByFullName(fullName);
	}

}
