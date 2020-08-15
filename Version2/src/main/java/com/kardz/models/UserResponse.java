package com.kardz.models;

public class UserResponse extends Response {

	public UserResponse(String response, String statusCode, Boolean isSuccessful, int responseCode) {
		super(response, statusCode, isSuccessful, responseCode);
	}
	
	public UserResponse(){
		super();

	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
