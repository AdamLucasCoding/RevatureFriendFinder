package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {
	// put a new user -- returns the new user object
	boolean createUser(User user);
	
	// get user by ID
	User getUserById(int id);
	
	// get all users by Id
	List<User> getAllUsers();	

	// User log-in
	User userLogIn(String username, String password);
	
	// User log-out
	boolean userLogOut(User user);
	
	// update user -- returns updated user object
	User updateUser(User user);

	// delete user
	boolean deleteUser(User user);
}
