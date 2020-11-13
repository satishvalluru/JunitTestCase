package com.java.service;

import com.java.dto.UserDTO;
import com.java.exception.InvalidCredentialsException;

public interface UserService {

	boolean saveUserDetails(UserDTO userDTO);

	boolean authenticate(String userName, String password) throws InvalidCredentialsException;

}
