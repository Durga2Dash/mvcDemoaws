package com.mindtree.dao;

import java.util.List;

import com.mindtree.entity.User;

public interface ValidationDao {
	String validateUser(User user);

	User getUserByUserName(String userName);

	List<User> getAllUsers();
}
