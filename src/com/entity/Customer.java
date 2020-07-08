package com.entity;

public class Customer {
	
	private Integer id;
	private String username;
	private String password;
	//private String email;
	
	private String customerName; 
	private String email;
	private String internetPackage;
	private String sector;
	private String customerID;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getInternetPackage() {
		return internetPackage;
	}
	
	public void setInternetPackage(String internetPackage) {
		this.internetPackage = internetPackage;
		
	}
	
	public String getSector() {
		return sector;
		
	}
	
	public void setSector(String sector) {
		this.sector= sector;
		
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	/*
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	*/ 
	
	
}
