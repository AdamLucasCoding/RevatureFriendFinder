package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.ClientMessage;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ClientMessageUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(path="/register")
	public @ResponseBody ClientMessage createUser(@RequestBody User user) {
		System.out.println("the user is: " + user.toString());
		return userService.create(user) ? ClientMessageUtil.CREATION_SUCCESSFUL : ClientMessageUtil.CREATION_FAILED;
	}

	@GetMapping(path="/{id}")
	public @ResponseBody User getById(@PathVariable int id) {
		return userService.findById(id);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody List<User> getAll() {
		return userService.findAll();
	}	
	
	@GetMapping(path="/login")
	public @ResponseBody User userLogIn(String username, String password) {
		return userService.logIn(username, password);
	}
	
	@GetMapping(path="/logout")
	public boolean userLogout(User user) {
		return userService.logOut(user);
	}
	
	@PutMapping(path="/")
	public @ResponseBody ClientMessage update (@RequestBody User user) {
		return userService.update(user) ? ClientMessageUtil.UPDATE_SUCCESSFUL : ClientMessageUtil.UPDATE_FAILED;
	}
	
	@DeleteMapping(path="/")
	public @ResponseBody ClientMessage deleteUser(@RequestBody User user) {
		userService.delete(user);
		return ClientMessageUtil.DELETION_SUCCESSFUL;
	}
}
