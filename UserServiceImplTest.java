package com.java;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.dao.UserDAO;
import com.java.dto.UserDTO;
import com.java.exception.InvalidCredentialsException;
import com.java.model.User;
import com.java.service.impl.UserServiceImpl;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserDAO userDao;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	static UserDTO userDto;

	static User user;
	static User userPersist;


	@BeforeAll
	public static void setUp() {
		userDto = new UserDTO();
		userDto.setUserName("jony");
		userDto.setPassword("1234");
		userDto.setMobile("9989999977");
		userDto.setEmail("jony@gmail.com");

		user = new User();
		user.setUserName("jony");
		user.setPassword("1234");
		user.setMobile("9989999977");
		user.setEmail("jony@gmail.com");
		
		userPersist = user;
		userPersist.setUserId(1);
		
		
	}

	@Test
	@DisplayName("Save UserDetails")
	public void saveUserDetailsTest() {
		when(userDao.saveUser(any(User.class))).thenAnswer(i -> {
			User user = i.getArgument(0);
			user.setUserId(1);
			return user;
		});
		boolean result = userServiceImpl.saveUserDetails(userDto);
		assertTrue(result);
	}
		/*
		 * when(userDao.saveUser(any(User.class))).thenReturn(userPersist); boolean
		 * result = userServiceImpl.saveUserDetails(userDto); assertTrue(result);
		 */
	
	
	@Test
	@DisplayName("Authenticate UserDetails : positive Scenerio")
	public void authenticateUserTest() throws InvalidCredentialsException {
		when(userDao.findByUserNameAndPassword("jony", "1234")).thenReturn(user);
		boolean result = userServiceImpl.authenticate("jony", "1234");
		assertTrue(result);
	}

	@Test
	@DisplayName("Authenticate UserDetails : negative Scenerio")
	public void authenticateUserTestNegative() throws InvalidCredentialsException {
		when(userDao.findByUserNameAndPassword("jony", "1234")).thenReturn(null);
		assertThrows(InvalidCredentialsException.class, () -> userServiceImpl.authenticate("jony", "1234"));
	}
}
