package com.pickify.service;

import java.util.List;

import com.pickify.exception.ProductException;
import com.pickify.model.Review;
import com.pickify.model.User;
import com.pickify.request.ReviewRequest;

public interface ReviewService {
	
	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long ProductId);
}
