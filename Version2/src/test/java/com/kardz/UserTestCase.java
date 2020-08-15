package com.kardz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.kardz.entity.UserEntity;
import com.kardz.models.Response;
import com.kardz.models.User;
import com.kardz.repository.UserRepository;
import com.kardz.service.UserServiceImplementation;

@SpringBootTest

class UserTestCase {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImplementation userServiceImplementation = new UserServiceImplementation();

	UserEntity userM = new UserEntity();
	

	@BeforeEach
	void setMockOutput() {
	    MockitoAnnotations.initMocks(this);

		userM.setEmail("test123@gmail.com");
		userM.setPassword("123456");
		userM.setUserId(1L);

		when(userRepository.existsById(userM.getEmail())).thenReturn(true, false);
		when(userRepository.save(userM)).thenReturn(userM);
		when(userRepository.getOne(userM.getEmail())).thenReturn(userM);
		when(userRepository.getOne(Mockito.anyString())).thenReturn(userM);

	}

	@DisplayName("User repository test")
	@Test
	void isEmailExist() {
		assertEquals(true, userServiceImplementation.isEmailExist("test123@gmail.com"));
	}
	
	@DisplayName("User repository getUser test")
	@Test
	void getUser() {
		assertEquals(userM, userServiceImplementation.getUser("test123@gmail.com"));
	}
	
//	@DisplayName("User repository getUserByID test")
//	@Test
//	void getUserById() {
//		assertEquals(userM, userServiceImplementation.getUserById(1L));
//	}

	@DisplayName("User repository signup test")
	@Test
	void signup() {
		User user = new User();
		user.setEmail("test123@gmail.com");
		user.setPassword("123456");

		Response response = new Response();
		response.setStatusCode("KZ000004");
		assertEquals(response.getStatusCode(), userServiceImplementation.signUp(user).getStatusCode());

		user.setEmail("testing@gmail.com");
		response.setStatusCode("KZ000005");
		assertEquals(response.getStatusCode(), userServiceImplementation.signUp(user).getStatusCode());

	}

	@DisplayName("User repository signin test")
	@Test
	void signin() {
		Response response = new Response();
		User user = new User();
		user.setEmail("testing@gmail.com");
		response.setStatusCode("KZ000003");
		assertEquals(response.getStatusCode(), userServiceImplementation.signin(user).getStatusCode());

		user = new User();
		user.setEmail("test123@gmail.com");
		user.setPassword("123456");
		response.setStatusCode("KZ000001");
		assertEquals(response.getStatusCode(), userServiceImplementation.signin(user).getStatusCode());

	}

	@DisplayName("User repository signin password incorrect")
	@Test
	void signin1() {
		Response response = new Response();
		User user = new User();
		user.setEmail("test123@gmail.com");
		user.setPassword("fkhvmhv");
		response.setStatusCode("KZ000002");
		assertEquals(response.getStatusCode(), userServiceImplementation.signin(user).getStatusCode());

	}
	
	
	@DisplayName("User repository update picture")
	@Test
	void updatePicture() {
		Response response = new Response();
		response.setStatusCode("KZ000006");

		User user = new User();
		
		user.setEmail("test123@gmail.com");
		user.setProfileImage("testing".getBytes());
		user.setIsProfileImageUpdated(true);
		assertEquals(response.getStatusCode(), userServiceImplementation.update(user).getStatusCode());
		
		response.setStatusCode("KZ000008");
		user = new User();
		user.setEmail("test123@gmail.com");
		user.setUserName("Tesing");
		user.setIsUserNameUpdated(true);
		user.setIsProfileImageUpdated(false);
		user.setIsPasswordUpdated(false);
		assertEquals(response.getStatusCode(), userServiceImplementation.update(user).getStatusCode());
		
		response.setStatusCode("KZ000007");
		 user = new User();
		user.setEmail("test123@gmail.com");
		user.setIsPasswordUpdated(true);
		user.setPassword("fkhvmhv");
		user.setIsProfileImageUpdated(false);
		//user.setIsUserNameUpdated(false);
		System.out.println(userServiceImplementation.update(user).getStatusCode());

		assertEquals(response.getStatusCode(), userServiceImplementation.update(user).getStatusCode());
		
		

	}
	

}
