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
		System.out.println("@UserController createUser is: " + user.toString());
		return userService.createUser(user);
	}

	@GetMapping(path="/user/id")
	public @ResponseBody User getById(@RequestBody int id) {
		System.out.println("@UserControl getById: " + id);
		return userService.getUserById(id);
	}
	
	@GetMapping(path="/users")
	public @ResponseBody List<User> getAll() {
		List<User> allUsers = userService.getAllUsers();
		for(User u : allUsers) {
			System.out.println("@UserController getAll: " + u.toString());			
		}
		return allUsers;
	}	
	
	@GetMapping(path="/login")
	public @ResponseBody User userLogIn(String username, String password) {
		return userService.userLogIn(username, password);
	}
	
	@GetMapping(path="/logout")
	public boolean userLogout(User user) {
		return userService.userLogOut(user);
	}
	
	@PutMapping(path="/user/update")
	public @ResponseBody User updateUser (@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping(path="/user/delete")
	public @ResponseBody boolean deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}
}
