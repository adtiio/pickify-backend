package com.pickify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickify.exception.OrderException;
import com.pickify.model.Order;
import com.pickify.response.ApiResponse;
import com.pickify.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	

	private OrderService orderService;
	
	public AdminOrderController() {
		// TODO Auto-generated constructor stub
	}
	public AdminOrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	public ResponseEntity<List<Order>> getAllOrdersHandler(){
		List<Order>orders=orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)  throws OrderException{
		
		Order order=orderService.shippedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)  throws OrderException{
		
		Order order=orderService.delivredOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cencelOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)  throws OrderException{
		
		Order order=orderService.cancledOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}delete")
	public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authrization") String jwt) throws OrderException{
		
		orderService.deleteOrder(orderId);
		ApiResponse res=new ApiResponse();
		res.setMessage("Order deleted Successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}	
