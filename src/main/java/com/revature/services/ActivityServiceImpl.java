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
	
	public ActivityServiceImpl(ActivityRepository dao) {
		this.arepo = dao;
	}

	@Override
	public List<Activity> getAllActivities() {
		return arepo.findAll();
	}

	@Override
	public Activity getActivityById(int id) {
		Activity tempActivity = arepo.findById(id).get();
		return tempActivity;
		//return arepo.findById(id).stream().findFirst().get();
	}

	@Override
	public boolean createActivity(Activity activity) {
		return arepo.save(activity) != null;
	}

	@Override
	public boolean updateActivity(Activity activity) {
		return arepo.save(activity) != null;
	}

	@Override
	public boolean deteteActivity(Activity activity) {
		arepo.delete(activity);
		return true;
	}

	@Override
	public List<Activity> getActivitiesByType(String type) {
		List<Activity> activitiesByType = arepo.findByType(type);
		return activitiesByType;
	}

	@Override
	public List<Activity>  getActivitiesByLocation(String location) {
		return arepo.findByLocation(location);
	}

	@Override
	public List<Activity>  getActivitiesByCreator(int id) {
		return  arepo.findByCreator(id);
	}
}
