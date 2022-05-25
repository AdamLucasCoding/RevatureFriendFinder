package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Activity;
import com.revature.repositories.ActivityRepository;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository arepo;
	
	@Override
	public List<Activity> getAllActivities() {
		List<Activity> allActivities = arepo.findAll();
		for(Activity a: allActivities) {
			System.out.println("@service layer.  allActivities: " + a.toString());
		}
		return allActivities;
	}

	@Override
	public Activity getActivityById(int id) {
		Activity tempActivity = arepo.findById(id).get();
		return tempActivity;
		//return arepo.findById(id).stream().findFirst().get();
	}

	@Override
	public Activity createActivity(Activity activity) {
		return arepo.save(activity);
	}

	@Override
	public Activity updateActivity(Activity activity) {
		return arepo.save(activity);
	}

	@Override
	public boolean deteteActivity(Activity activity) {
		arepo.delete(activity);
		return true;
	}

	@Override
	public List<Activity> getActivitiesByType(String type) {
		System.out.println("Looking for type: " + type);
		List<Activity> activitiesByType = arepo.findByType(type);
		System.out.println("actvitiesByType: " + activitiesByType.toString());
		for(Activity a : activitiesByType) {
			System.out.println("@service, getActivitesByType: " + a.toString());
		}
		return activitiesByType;
	}

	@Override
	public List<Activity>  getActivitiesByLocation(String location) {
		List<Activity> activitiesByLocation = arepo.findByLocation(location);
		return activitiesByLocation;
	}

	@Override
	public List<Activity>  getActivitiesByCreator(int id) {
		List<Activity> activitiesByCreator = arepo.findByCreator(id);
		return activitiesByCreator;
	}
}
