package com.pickify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickify.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
