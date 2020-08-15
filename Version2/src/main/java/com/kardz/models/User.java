package com.kardz.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



public class User {
	
	
	private String userName;
	private String email;
	private String password;
	private byte[] profileImage;
	private Long userId;
	private Boolean isLoggedIn;
	private Boolean isProfileImageUpdated;
	private Boolean isUserNameUpdated;
	private Boolean isPasswordUpdated;
	private String date;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public Boolean getIsProfileImageUpdated() {
		return isProfileImageUpdated;
	}
	public void setIsProfileImageUpdated(Boolean isProfileImageUpdated) {
		this.isProfileImageUpdated = isProfileImageUpdated;
	}
	public Boolean getIsUserNameUpdated() {
		return isUserNameUpdated;
	}
	public void setIsUserNameUpdated(Boolean isUserNameUpdated) {
		this.isUserNameUpdated = isUserNameUpdated;
	}
	public Boolean getIsPasswordUpdated() {
		return isPasswordUpdated;
	}
	public void setIsPasswordUpdated(Boolean isPasswordUpdated) {
		this.isPasswordUpdated = isPasswordUpdated;
	}
	public String getDate() {
		return date;
	}
	public void setDate() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.date = myDateObj.format(myFormatObj);
		
	}

}
