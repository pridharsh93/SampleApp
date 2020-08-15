package com.kardz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kardz.entity.UserEntity;
import com.kardz.models.Response;
import com.kardz.models.User;
import com.kardz.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	// http://localhost:8080/api/user/login 
	
	@PutMapping("/login")
	public Response login(User user){
		if(user.getEmail() == null || user.getPassword() == null){
			return new Response("your email or password is empty","KZ000010",Boolean.FALSE,HttpStatus.NO_CONTENT.value());
		}
		
		return userService.signin(user);
	}

	@PostMapping("/signup")
	public Response signup(User user){
		if(user.getEmail() == null || user.getPassword() == null || user.getUserName() == null){
			return new Response("Kindly input the mandatory fields: userName, password and email ID","KZ000011",Boolean.FALSE,HttpStatus.NO_CONTENT.value());
		}
		
		return userService.signUp(user);
	}
	
	@PutMapping("/update")
	public Response update(User user){
		if(user.getUserName() == null && user.getPassword() == null && user.getProfileImage() == null){
			return new Response("Kindly input the field to be updated","KZ000012",Boolean.FALSE,HttpStatus.NO_CONTENT.value());
		}
		
		return userService.update(user);
	}
	
	@GetMapping("/{emailID}")
	public UserEntity getUser(@PathVariable(name = "emailID") String email){
		if(email == null){
			throw new NullPointerException("Enter a valid user ID");
		}
		
		return userService.getUser(email);
	}
	
//	@GetMapping("/id/{id}")
//	public User getUserByID(@PathVariable(name = "id") Long userID){
//		if(userID == null){
//			throw new NullPointerException("Enter a valid email ID");
//		}
//		/*
//		 * separte field need to handle
//		 */
//		return userService.getUserById(userID);
//	}
}
