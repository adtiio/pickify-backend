package com.pickify.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.stereotype.Service;

import com.pickify.exception.ProductException;
import com.pickify.model.Product;
import com.pickify.model.Review;
import com.pickify.model.User;
import com.pickify.repository.ProductRepository;
import com.pickify.repository.ReviewRepository;
import com.pickify.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	

	public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {

		Product product=productService.findProductById(req.getProductId());
		
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long ProductId) {
		return reviewRepository.getAllProductsReview(ProductId);
	}

}
