package com.kardz.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kardz.entity.UserEntity;
import com.kardz.models.Response;
import com.kardz.models.User;
import com.kardz.models.UserResponse;
import com.kardz.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public Response signin(User user) {
		
		UserEntity userFromDB;
		UserResponse userResponse = new UserResponse();
		System.out.println("input user"+user.getEmail());
		
		boolean isEmailExist = !isEmailExist(user.getEmail());
		System.out.println("isEmailexist "+isEmailExist);
		if(isEmailExist){
			Response response = new Response("your email is incorrect or  do signup","KZ000003",Boolean.FALSE,HttpStatus.ACCEPTED.value());
			userResponse=setResponse(userResponse,response);
			return userResponse;
		}else{
			userFromDB = getUser(user.getEmail());
		}
		
		System.out.println("output user"+userFromDB.getEmail());
		System.out.println(userFromDB.getPassword()+" ---> "+user.getPassword());
		if(userFromDB.getPassword().equals(user.getPassword())){ 
			userFromDB.setIsLoggedIn(Boolean.TRUE);
			//userFromDB.setDate();
			userResponse.setUser(user);
			Response response = new Response("your succesfully logged in","KZ000001",Boolean.TRUE,HttpStatus.OK.value());
			userResponse=setResponse(userResponse,response);
			return userResponse;
		}else{
			userFromDB.setIsLoggedIn(Boolean.FALSE);
			//userFromDB.setDate();
			Response response = new Response("your password is incorreect ","KZ000002",Boolean.FALSE,HttpStatus.UNAUTHORIZED.value());
			userResponse=setResponse(userResponse,response);
		}
		
		return userResponse;
	}



	@Override
	public Response signUp(User user) {
		//User userFromDb;
		UserResponse userResponse = new UserResponse();
		if(isEmailExist(user.getEmail())){
			Response response = new Response("your emailID already exists, kindly login","KZ000004",Boolean.FALSE,HttpStatus.UNAUTHORIZED.value());
			userResponse=setResponse(userResponse,response);
			return userResponse;
		}  
		UserEntity userEntity = setEntity(user);
		UserEntity userFromDb = userRepository.save(userEntity);
			//userFromDb.setDate();
			Response response = new Response("Successfully signed up","KZ000005",Boolean.TRUE,HttpStatus.ACCEPTED.value());
			userResponse.setUser(user);
			userResponse=setResponse(userResponse,response);
		return userResponse;
		
	}

	@Override
	public Response update(User user) {
		UserEntity userFromDb = getUser(user.getEmail());
		UserResponse userResponse = new UserResponse();
		if(user.getIsProfileImageUpdated()) { // better option: calling user object each time to get the inputs or creating local variable
			userFromDb.setProfileImage(user.getProfileImage());
			userFromDb = userRepository.save(userFromDb);//save() in Spring Data JPA is backed by merge() in plain JPA
			Response response = new Response("Profile picture updated","KZ000006",Boolean.TRUE,HttpStatus.ACCEPTED.value());
			userResponse.setUser(user);
			userResponse=setResponse(userResponse,response);
		return userResponse;		
		}else 
			if(user.getIsPasswordUpdated()) { // better option: calling user object each time to get the inputs or creating local variable
				userFromDb.setPassword(user.getPassword());
				userFromDb =	userRepository.save(userFromDb);
				Response response = new Response("Password updated","KZ000007",Boolean.TRUE,HttpStatus.ACCEPTED.value());
				userResponse.setUser(user);
				userResponse=setResponse(userResponse,response);
			return userResponse;		
			} else
				if(user.getIsUserNameUpdated()) { // better option: calling user object each time to get the inputs or creating local variable
					userFromDb.setUserName(user.getUserName());
					userFromDb=	userRepository.save(userFromDb);
					Response response = new Response("UserName updated","KZ000008",Boolean.TRUE,HttpStatus.ACCEPTED.value());
					userResponse.setUser(user);
					userResponse=setResponse(userResponse,response);
			}
		return userResponse;
		
	}

	@Override
	public UserEntity getUser(String email)  {
		
		UserEntity userFromDb = userRepository.getOne(email);	
		if(userFromDb == null)
			throw new EntityNotFoundException("no such user exists");
		return userFromDb;
	}

	@Override
	public Boolean isEmailExist(String email) {
		return userRepository.existsById(email);//(returns Optional)<--findById().isPresent() //isPresent returns User -> existsById (returns User)
	}
	
	private UserResponse setResponse(UserResponse userResponse ,Response response) {
		userResponse.setResponse(response.getResponse());
		userResponse.setStatusCode(response.getStatusCode());
		userResponse.setIsSuccessFul(response.getIsSuccessFul());
		userResponse.setResponseCode(response.getResponseCode());
		return userResponse;
	}
	
	private UserEntity setEntity(User user) {
		UserEntity userFromDb = new UserEntity();
		userFromDb.setUserName(user.getUserName());
		userFromDb.setEmail(user.getEmail());
		userFromDb.setPassword(user.getPassword());
		userFromDb.setProfileImage(user.getProfileImage());
		return userFromDb;
	}

//	@Override
//	public User getUserById(Long userId) throws EntityNotFoundException {
//		String userID = String.valueOf(userId); //is this a good practise
//		User userFromDb = userRepository.getOne(userID);
//		if(userFromDb == null) 
//			throw new EntityNotFoundException("no such user exists");
//		return userFromDb;
//		//return null;
//	}

}
