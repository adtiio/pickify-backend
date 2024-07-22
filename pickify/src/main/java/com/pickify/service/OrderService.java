package com.pickify.service;

import java.util.List;

import com.pickify.exception.OrderException;
import com.pickify.model.Address;
import com.pickify.model.Order;
import com.pickify.model.User;

public interface OrderService {
	
	public Order createOrder(User user, Address shippingAddress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placeOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order delivredOrder(Long orderId)throws OrderException;
	
	public Order cancledOrder(Long orderId)throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException; 
}
