package com.pickify.request;


public class ReviewRequest {

	public Long productId;
	private String review;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public ReviewRequest(Long productId, String review) {
		super();
		this.productId = productId;
		this.review = review;
	}
	
	public ReviewRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
}
