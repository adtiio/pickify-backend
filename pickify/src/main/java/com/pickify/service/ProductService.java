package com.pickify.service;

import com.pickify.exception.ProductException; 
import com.pickify.model.Product;
import com.pickify.request.CreateProductRequest;

import java.util.*;

import org.springframework.data.domain.Page;


public interface ProductService {

	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product req) throws ProductException;
	
	public Product findProductById(Long id)throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public Page<Product> getAllProduct(String category, List<String>colors,List<String>sizes, Integer minPrice, Integer maxPrice, Integer minDiscountInteger, String sort, String stock,Integer pageNumber, Integer pageSize );
	
	public List<Product> findAllProducts();
}
