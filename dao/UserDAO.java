package com.java.dao;

import com.java.dto.UserLoginDTO;
import com.java.model.User;

public interface UserDAO {

	public User saveUser(User user);

	public Boolean authenticate(UserLoginDTO userLoginDTO);

	public User findByUserNameAndPassword(String userName, String password);

}
