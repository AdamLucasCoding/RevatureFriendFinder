package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping(path="/activity/create")
	public @ResponseBody ClientMessage createActivity(@RequestBody Activity activity) {
		return activityService.createActivity(activity) ? ClientMessageUtil.CREATION_SUCCESSFUL : ClientMessageUtil.CREATION_FAILED;
	}
	
	@GetMapping(path="/activity/all")
	public @ResponseBody List<Activity> getAll() {
		List<Activity> allActivities = activityService.getAllActivities();
		return allActivities;
	}
	
	@GetMapping(path="/activity")
	public @ResponseBody Activity getById(@RequestParam(value = "id", name = "id") int id) {
		return activityService.getActivityById(id);
	}
	
	@GetMapping(path="/activity/bytype")
	public @ResponseBody List<Activity> getByType(@RequestBody String type) {
		List<Activity> activitiesByType = activityService.getActivitiesByType(type);
		return activitiesByType;
	}
	
	@GetMapping(path="/activity/bylocation")
	public @ResponseBody List<Activity> getByLocation(@RequestBody String location) {
		return activityService.getActivitiesByLocation(location);
	}
	
	@GetMapping(path="/activity/bycreator")
	public @ResponseBody List<Activity> getByCreator(@RequestBody int id) {
		return activityService.getActivitiesByCreator(id);
	}
	
	@PutMapping(path="/activity/update")
	public @ResponseBody ClientMessage updateActivity(@RequestBody Activity activity) {
		return activityService.updateActivity(activity) ? ClientMessageUtil.UPDATE_SUCCESSFUL : ClientMessageUtil.UPDATE_FAILED;
	}
	
	@DeleteMapping(path="/activity/delete")
	public @ResponseBody ClientMessage deleteUser(@RequestBody Activity activity) {
		return activityService.deteteActivity(activity) ? ClientMessageUtil.DELETION_SUCCESSFUL : ClientMessageUtil.DELETION_FAILED;
	}

}
