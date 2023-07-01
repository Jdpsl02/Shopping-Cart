package com.eshoppingzone.resource;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eshoppingzone.orders.model.Address;
import com.eshoppingzone.orders.model.Orders;

import com.eshoppingzone.orders.resource.DeletingCart;
import com.eshoppingzone.orders.resource.OrderResource;
import com.eshoppingzone.orders.resource.PlacingOrder;
import com.eshoppingzone.orders.service.OrderService;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= {OrderControllerTests.class})
class OrderControllerTests {

	  @Mock
	    private OrderService orderService;

	    @Mock
	    private PlacingOrder pl;

	    @Mock
	    private DeletingCart dl;

	    @InjectMocks
	    private OrderResource orderResource;

	   
		@BeforeEach
	    void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    void testGetAllOrders() {
	        // Mock data
	        List<Orders> ordersList = new ArrayList<>();
	        // Mocking orderService.getAllOrders() to return the mock data
	        when(orderService.getAllOrders()).thenReturn(ordersList);

	        // Call the API
	        List<Orders> result = orderResource.getAllOrders();

	        // Verify the result
	        assertEquals(ordersList, result);
	        verify(orderService, times(1)).getAllOrders();
	    }

	    @Test
	    void testGetAllAddress() {
	        // Mock data
	        List<Address> addressList = new ArrayList<>();
	        // Mocking orderService.getAllAddress() to return the mock data
	        when(orderService.getAllAddress()).thenReturn(addressList);

	        // Call the API
	        List<Address> result = orderResource.getAllAddress();

	        // Verify the result
	        assertEquals(addressList, result);
	        verify(orderService, times(1)).getAllAddress();
	    }

	    @Test
	    void testGetOrderByCustomerId() {
	        int customerId = 1;
	        // Mock data
	        List<Orders> ordersList = new ArrayList<>();
	        // Mocking orderService.getOrderByCustomerId() to return the mock data
	        when(orderService.getOrderByCustomerId(customerId)).thenReturn(ordersList);

	        // Call the API
	        List<Orders> result = orderResource.getOrderByCustomerId(customerId);

	        // Verify the result
	        assertEquals(ordersList, result);
	        verify(orderService, times(1)).getOrderByCustomerId(customerId);
	    }	    
	    
	    
	    @Test
	    void testDeleteOrder() {
	        int orderId = 1;

	        // Call the API
	        ResponseEntity<String> response = orderResource.deleteOrder(orderId);

	        // Verify the result
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Your order is deleted with" + orderId, response.getBody());
	        verify(orderService, times(1)).deleteOrder(orderId);
	    }

	    @Test
	    void testGetOrderByfullName() {
	        String fullName = "John Doe";
	        // Mock data
	        List<Orders> ordersList = new ArrayList<>();
	        // Mocking orderService.findOrderByFullName() to return the mock data
	        when(orderService.findOrderByFullName(fullName)).thenReturn(ordersList);

	        // Call the API
	        List<Orders> result = orderResource.getOrderByfullName(fullName);

	        // Verify the result
	        assertEquals(ordersList, result);
	        verify(orderService, times(1)).findOrderByFullName(fullName);
	        }
}
