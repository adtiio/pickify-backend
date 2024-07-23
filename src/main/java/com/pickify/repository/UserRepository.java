package com.pickify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickify.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
}