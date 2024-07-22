package com.pickify.service;

import com.pickify.exception.ProductException;
import com.pickify.model.Cart;
import com.pickify.model.User;
import com.pickify.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
