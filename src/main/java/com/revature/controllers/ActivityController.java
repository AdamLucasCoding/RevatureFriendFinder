package com.revature.controllers;
import java.util.List;
import java.util.Optional;

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

import com.revature.models.Activity;
import com.revature.models.ClientMessage;
import com.revature.services.ActivityService;
import com.revature.util.ClientMessageUtil;

@RestController
@RequestMapping("/api")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;

	@PostMapping(path="/register")
	public @ResponseBody ClientMessage createUser(@RequestBody Activity activity) {
		return activityService.create(activity) ? ClientMessageUtil.CREATION_SUCCESSFUL : ClientMessageUtil.CREATION_FAILED;
	}

	@GetMapping(path="/activity/{id}")
	public @ResponseBody Activity getById(@PathVariable int id) {
		Optional<Activity> activity = activityService.findById(id);
		return activity.isPresent()? activity.get() : null;
	}
	
	@GetMapping(path="/users")
	public @ResponseBody List<Activity> getAll() {
		return activityService.findAll();
	}
	
	@PutMapping(path="/activity")
	public @ResponseBody ClientMessage UpdateUser (@RequestBody Activity activity) {
		return activityService.update(activity) ? ClientMessageUtil.UPDATE_SUCCESSFUL :  ClientMessageUtil.UPDATE_FAILED;
	}
	
	@DeleteMapping(path="/activity")
	public @ResponseBody ClientMessage deleteUser(@RequestBody Activity activity) {
		activityService.delete(activity);
		return ClientMessageUtil.DELETION_SUCCESSFUL;
	}

}
