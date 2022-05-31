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
import io.swagger.annotations.*;

@RestController
@RequestMapping("/api/activity")
@Api(value = "ActivityRestController")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping(path = "/register")
	@ApiOperation(value = "Create a new activity entity")
	public @ResponseBody ClientMessage create(@RequestBody Activity activity) {
		return activityService.create(activity) ? ClientMessageUtil.CREATION_SUCCESSFUL
				: ClientMessageUtil.CREATION_FAILED;
	}

	@ApiOperation(value = "Find activity by unique id", notes = "Provide an unique id to lookup a specific activity", response = Activity.class)
	@GetMapping(path = "/{id}")
	public @ResponseBody Activity findById(@PathVariable int id) {
		return activityService.findById(id);
	}

	@ApiOperation(value = "Find all activities")
	@GetMapping(path = "/all")
	public @ResponseBody List<Activity> findAll() {
		return activityService.findAll();
	}

	@PutMapping(path = "/")
	@ApiOperation(value = "Update an existing activity entity")
	public @ResponseBody ClientMessage update(@RequestBody Activity activity) {
		return activityService.update(activity) ? ClientMessageUtil.UPDATE_SUCCESSFUL : ClientMessageUtil.UPDATE_FAILED;
	}

	@DeleteMapping(path = "/")
	@ApiOperation(value = "Delete an existing activity entity")
	public @ResponseBody ClientMessage delete(@RequestBody Activity activity) {
		activityService.delete(activity);
		return ClientMessageUtil.DELETION_SUCCESSFUL;
	}

}
