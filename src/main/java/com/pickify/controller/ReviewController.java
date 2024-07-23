package com.pickify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickify.exception.ProductException;
import com.pickify.exception.UserException;
import com.pickify.model.Review;
import com.pickify.model.User;
import com.pickify.request.ReviewRequest;
import com.pickify.service.ReviewService;
import com.pickify.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
			@RequestHeader("Authrization") String jwt) throws UserException,ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		Review review=reviewService.createReview(req, user);
		
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		List<Review> ratings=reviewService.getAllReview(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.OK);
	}
}
