package com.pickify.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pickify.exception.ProductException;
import com.pickify.model.Product;
import com.pickify.model.Rating;
import com.pickify.model.User;
import com.pickify.repository.RatingRepository;
import com.pickify.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductService productService;
	
	

	public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
		super();
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {

		Product product= productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepository.getAllProductsRating(productId);
	}

}
