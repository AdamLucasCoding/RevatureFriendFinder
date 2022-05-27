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

import com.revature.models.ClientMessage;
import com.revature.models.User;
import com.revature.services.UserService;
import static com.revature.util.ClientMessageUtil.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(path="/register")
	public @ResponseBody ClientMessage createUser(@RequestBody User user) {
		return userService.createUser(user) ? CREATION_SUCCESSFUL : CREATION_FAILED;
	}

	@GetMapping(path="/id")
	public @ResponseBody User getById(@RequestBody int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(path="/getall")
	public @ResponseBody List<User> getAll() {
		List<User> allUsers = userService.getAllUsers();
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
	
	@PutMapping(path="/update")
	public @ResponseBody ClientMessage updateUser (@RequestBody User user) {
		return userService.updateUser(user) ? UPDATE_SUCCESSFUL : UPDATE_FAILED;
	}
	
	@DeleteMapping(path="/delete")
	public @ResponseBody ClientMessage deleteUser(@RequestBody User user) {
		return userService.deleteUser(user) ? DELETION_SUCCESSFUL : DELETION_FAILED;
	}
}
