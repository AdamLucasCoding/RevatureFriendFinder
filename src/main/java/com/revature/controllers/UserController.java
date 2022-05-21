package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userv;

	@GetMapping(path="/register")
	public @ResponseBody User createUser(@RequestBody User user) {
		return userv.createUser(user);
	}

	@GetMapping(path="/user/{id}")
	public @ResponseBody User getById(@RequestBody int id) {
		return userv.getUserById(id);
	}
	
	@GetMapping(path="/users")
	public @ResponseBody List<User> getAll() {
		return userv.getAllUsers();
	}	
	
	@GetMapping(path="/login")
	public @ResponseBody User userLogIn(String username, String password) {
		return userv.userLogIn(username, password);
	}
	
	@GetMapping(path="/user/{id}/logout")
	public boolean userLogout(User user) {
		return userv.userLogOut(user);
	}
	
	@GetMapping(path="/user/{id}/update")
	public @ResponseBody User UpdateUser (@RequestBody User user) {
		return userv.updateUser(user);
	}
	
	@GetMapping(path="/user/{id}/delete")
	public boolean deleteUser(@RequestBody User user) {
		return userv.deleteUser(user);
	}
}
