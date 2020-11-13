package com.java.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.java.dao.UserDAO;
import com.java.dto.UserDTO;
import com.java.exception.InvalidCredentialsException;
import com.java.model.User;
import com.java.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDao;

	@Override
	public boolean saveUserDetails(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		User userPers = userDao.saveUser(user);
		if(ObjectUtils.isEmpty(userPers)) return false;
		return true;
	}

	@Override
	public boolean authenticate(String userName, String password) throws InvalidCredentialsException {
		User user = userDao.findByUserNameAndPassword(userName, password);
		if(user!=null)
			return true;
		throw new InvalidCredentialsException("Invalid Credentials...!! Please verify your credentials and try again");
	}

}
