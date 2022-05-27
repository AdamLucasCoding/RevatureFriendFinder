package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {
	// put a new user -- returns the new user object
	boolean create(User user);
	
	// get user by ID
	User findById(int id);
	
	// get all users by Id
	List<User> findAll();	

	// User log-in
	User logIn(String username, String password);
	
	// User log-out
	boolean logOut(User user);
	
	// update user -- returns updated user object
	boolean update(User user);

	// delete user
	boolean delete(User user);
}
