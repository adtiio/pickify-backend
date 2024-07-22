package com.pickify.service;

import com.pickify.exception.CartItemException;
import com.pickify.exception.UserException;
import com.pickify.model.Cart;
import com.pickify.model.CartItem;
import com.pickify.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
