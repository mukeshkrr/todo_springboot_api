package com.todos.model;

public class CustomSession {
	
	private Long id;
	private String email;
	private Boolean isLoggedIn;
	
	
	
	public CustomSession(Long id, String email, Boolean isLoggedIn) {
		super();
		this.id = id;
		this.email = email;
		this.isLoggedIn = isLoggedIn;
	}
	
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	

}
