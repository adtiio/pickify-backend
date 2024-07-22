package com.pickify.model;

public class PaymentDetails {

	private String paymentMethod;
	private String paymentStatus;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkRefrenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpayPaymentId;
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}
	public String getRazorpayPaymentLinkRefrenceId() {
		return razorpayPaymentLinkRefrenceId;
	}
	public void setRazorpayPaymentLinkRefrenceId(String razorpayPaymentLinkRefrenceId) {
		this.razorpayPaymentLinkRefrenceId = razorpayPaymentLinkRefrenceId;
	}
	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}
	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public PaymentDetails() {
		super();
	}
	public PaymentDetails(String paymentMethod, String paymentStatus, String paymentId, String razorpayPaymentLinkId,
			String razorpayPaymentLinkRefrenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkRefrenceId = razorpayPaymentLinkRefrenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}
	
	
	
	
}
