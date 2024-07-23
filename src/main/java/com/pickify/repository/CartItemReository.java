package com.pickify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pickify.model.Cart;
import com.pickify.model.CartItem;
import com.pickify.model.Product;

public interface CartItemReository extends JpaRepository<CartItem, Long>{

	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart= :cart AND ci.product= :product AND ci.size= :size AND ci.userId= :userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product")Product product, @Param("size")String size, @Param("userId")Long userId);
}
