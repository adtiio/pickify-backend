package com.pickify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickify.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	
}
