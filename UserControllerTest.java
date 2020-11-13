package com.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.controller.UserController;
import com.java.dto.UserDTO;
import com.java.dto.UserLoginDTO;
import com.java.exception.InvalidCredentialsException;
import com.java.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	static UserDTO userDto;
	
	static UserLoginDTO loginDTO;
	
	@BeforeAll
	public static void setUp() {
		userDto = new UserDTO();
		userDto.setUserName("jony");
		userDto.setPassword("1234");
		userDto.setMobile("9989999977");
		userDto.setEmail("jony@gmail.com");
		
		loginDTO = new UserLoginDTO();
		loginDTO.setUserName("sat");
		loginDTO.setPassword("123456");
	}
	
	@Test
	@DisplayName("Registration function: Positive Scenerio")
	public void registerTest() {
		when(userService.saveUserDetails(userDto)).thenReturn(true);
		String result = userController.register(userDto);
		verify(userService).saveUserDetails(userDto);
		assertEquals("Registration Success", result);
	}
	
	@Test
	@DisplayName("Registration function: Negative Scenerio")
	public void registerTestNegative() {
		when(userService.saveUserDetails(userDto)).thenReturn(false);
		String result = userController.register(userDto);
		verify(userService).saveUserDetails(userDto);

		assertEquals("Registration Failed", result);
	}
	
	@Test
	@DisplayName("Login function: Positive Scenerio")
	public void loginTest() throws InvalidCredentialsException {
		when(userService.authenticate("sat", "123456")).thenReturn(true);
		String result = userController.login(loginDTO);
		assertEquals("Login Success", result);
		
	}

}
