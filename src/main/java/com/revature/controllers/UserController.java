package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private UserService userService;

	@PostMapping(path="/register")
	public @ResponseBody User createUser(@RequestBody User user) {
		System.out.println("user: " + user);
		return userService.createUser(user);
	}

	@GetMapping(path="/user/{id}")
	public @ResponseBody User getById(@RequestBody int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(path="/users")
	public @ResponseBody List<User> getAll() {
		return userService.getAllUsers();
	}	
	
	@GetMapping(path="/login")
	public @ResponseBody User userLogIn(String username, String password) {
		return userService.userLogIn(username, password);
	}
	
	@GetMapping(path="/logout")
	public boolean userLogout(User user) {
		return userService.userLogOut(user);
	}
	
	@PutMapping(path="/user")
	public @ResponseBody User UpdateUser (@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping(path="/user")
	public @ResponseBody boolean deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}
}
