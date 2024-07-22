
package com.pickify.controller;

import com.pickify.exception.CartItemException;
import com.pickify.exception.UserException;
import com.pickify.model.CartItem;
import com.pickify.model.User;
import com.pickify.repository.UserRepository;
import com.pickify.response.ApiResponse;
import com.pickify.service.CartItemService;
import com.pickify.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
    private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
    
    
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt) throws UserException,CartItemException{
		
		User user=userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse response=new ApiResponse();
		response.setMessage("item deleted successfully ");
		response.setStatus(true);
		
		return new ResponseEntity<> (response,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt,@RequestBody CartItem cartItem) throws UserException,CartItemException{
		
		
		
		User user=userService.findUserProfileByJwt(jwt);
		Long id =user.getId();
		
		CartItem cartItem2=cartItemService.updateCartItem(id, cartItemId, cartItem);
		
		
		return new ResponseEntity<> (cartItem2,HttpStatus.OK);
	}
}
