package com.pickify.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pickify.exception.OrderException;
import com.pickify.model.Address;
import com.pickify.model.Cart;
import com.pickify.model.CartItem;
import com.pickify.model.Order;
import com.pickify.model.OrderItem;
import com.pickify.model.User;
import com.pickify.repository.AddressRepository;
import com.pickify.repository.OrderItemRepository;
import com.pickify.repository.OrderRepository;
import com.pickify.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	private CartService cartService;
	private OrderRepository orderRepository;
	private AddressRepository addressRepository; 
	private OrderItemRepository orderItemRepository;
	private UserRepository userRepository;
	
	public OrderServiceImpl(UserRepository userRepository,
		 CartService cartService, OrderRepository orderRepository,AddressRepository addressRepository,OrderItemRepository orderItemRepository) {
		
		this.userRepository=userRepository;
		this.cartService=cartService;
		this.orderRepository=orderRepository;
		this.addressRepository=addressRepository;
		this.orderItemRepository=orderItemRepository;
		
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {

		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			orderItems.add(createdOrderItem);
			
		}
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setPaymentStatus("PENDING");
		createdOrder.setCreateAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item :orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {

		Optional<Order>optional=orderRepository.findById(orderId) ;
		
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new OrderException("Order not exist with id " + orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		List<Order> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placeOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("Placed");
		order.getPaymentDetails().setPaymentStatus("Completed");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("Comfirmed");

		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("Shipped");
		
		return orderRepository.save(order);
	}

	@Override
	public Order delivredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("Delivred");
		
		return orderRepository.save(order);
	}

	@Override
	public Order cancledOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("Cancel");
		
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		
		Order order=findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

}
