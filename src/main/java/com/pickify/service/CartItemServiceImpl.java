package com.pickify.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pickify.exception.CartItemException;
import com.pickify.exception.UserException;
import com.pickify.model.Cart;
import com.pickify.model.CartItem;
import com.pickify.model.Product;
import com.pickify.model.User;
import com.pickify.repository.CartItemReository;
import com.pickify.repository.CartRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	private CartItemReository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;
	
	

	public CartItemServiceImpl(CartItemReository cartItemRepository, UserService userService,
			CartRepository cartRepository) {
		super();
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()* cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountPrice() * cartItem.getQuantity());
		
		CartItem createdCartItem=cartItemRepository.save(cartItem);
		
	
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item=findCartItemById(id);
		User user = userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity() * item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountPrice() * item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		
		User reqUser=userService.findUserById(userId);
		if(user.getId().equals(reqUser.getId())){
			cartItemRepository.deleteById(cartItemId);
		}else {
			throw new UserException("You can't remove another suers item");
		}
		
		
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {

		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		
		if(cartItem.isPresent()) {
			return cartItem.get();
		}
		throw new CartItemException("Cart Item not found with id: "+cartItemId);
		
	}

}
