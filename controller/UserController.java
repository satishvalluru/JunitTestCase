package com.java.controller;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.UserDTO;
import com.java.dto.UserLoginDTO;
import com.java.exception.InvalidCredentialsException;
import com.java.service.UserService;

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public String register(@RequestBody UserDTO userDTO) {
		logger.debug("Started Register Method");
		Boolean isExists = userService.saveUserDetails(userDTO);
		if (isExists)
			return "Registration Success";
		return "Registration Failed";
	}

	@PostMapping("/users/login")
	public String login(@Valid @RequestBody UserLoginDTO loginDTO) throws InvalidCredentialsException {
		boolean isExists = userService.authenticate(loginDTO.getUserName(), loginDTO.getPassword());
		if(isExists) return "Login Success";
		return "Invalid Credentials";
	}

}
