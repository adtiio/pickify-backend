package com.pickify.service;

import java.util.List;

import com.pickify.exception.ProductException;
import com.pickify.model.Rating;
import com.pickify.model.User;
import com.pickify.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId); 
}
