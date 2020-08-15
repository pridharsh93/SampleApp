package com.kardz.service;

import javax.persistence.EntityNotFoundException;

import com.kardz.entity.UserEntity;
import com.kardz.models.Response;
import com.kardz.models.User;

public interface UserService {

	public Response signin(User user );
	public Response signUp(User user );
	public Response update(User user); // update
	public UserEntity getUser(String email) throws EntityNotFoundException ;
	public Boolean isEmailExist(String email);
	//public User getUserById(Long userId) throws EntityNotFoundException ;

}
