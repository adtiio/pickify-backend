package com.pickify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickify.exception.ProductException;
import com.pickify.exception.UserException;
import com.pickify.model.Cart;
import com.pickify.model.User;
import com.pickify.request.AddItemRequest;
import com.pickify.response.ApiResponse;
import com.pickify.service.CartService;
import com.pickify.service.UserService;

import ch.qos.logback.core.pattern.color.RedCompositeConverter;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException{
		User user=userService.findUserProfileByJwt(jwt);
		Cart cart= cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization")String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		cartService.addCartItem(user.getId(), req);
		
		ApiResponse response=new ApiResponse();
		response.setMessage("Item added to cart");
		response.setStatus(true);
		System.out.println(req.getSize());
		
		return new ResponseEntity<>(response,HttpStatus.OK); 
	}
	
	
}
