package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.AuthenticationRequest;
import com.revature.models.AuthenticationResponse;
import com.revature.models.User;
import com.revature.services.MyUserDetailService;
import com.revature.services.UserService;
import com.revature.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MyUserDetailService userDetailService;

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping(path = "/register")
	public @ResponseBody User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPword());
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@GetMapping(path = "/user/id")
	public @ResponseBody User getById(@RequestBody int id) {
		return userService.getUserById(id);
	}

	@GetMapping(path = "/users")
	public @ResponseBody List<User> getAll() {
		List<User> allUsers = userService.getAllUsers();
		return allUsers;
	}

	@GetMapping(path = "/login")
	public @ResponseBody User userLogIn(String username, String password) {
		return null;
	}

	@GetMapping(path = "/logout")
	public boolean userLogout(User user) {
		return userService.userLogOut(user);
	}

	@PutMapping(path = "/user/update")
	public @ResponseBody User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping(path = "/user/delete")
	public @ResponseBody boolean deleteUser(@RequestBody User user) {
		return userService.deleteUser(user);
	}
}
