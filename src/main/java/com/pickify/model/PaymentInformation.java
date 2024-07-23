package com.pickify.model;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PaymentInformation {
	
	@Column(name="cardholder_name")
	public String cardholderName;
	
	@Column(name = "card_number")
	public String cardNumber;
	
	@Column(name = "expiration_date")
	public LocalDate expirationDate;
	
	@Column(name = "cvv")
	public String cvv;
	
}
