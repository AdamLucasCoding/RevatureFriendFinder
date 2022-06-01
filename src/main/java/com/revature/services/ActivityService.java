package com.revature.services;

import java.util.List;

import com.revature.models.Activity;

public interface ActivityService {
	
	// get all Activities
	List<Activity> getAllActivities();

	Activity getActivityById(int id);

	boolean createActivity(Activity activity);

	boolean updateActivity(Activity activity);

	boolean deteteActivity(Activity activity);

	List<Activity> getActivitiesByType(String type);

	List<Activity> getActivitiesByLocation(String location);

	List<Activity> getActivitiesByCreator(int id);

	// search by params
	
	// get activity by id
	

}
