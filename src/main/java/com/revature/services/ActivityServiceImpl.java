package com.revature.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Activity;
import com.revature.repositories.ActivityRepository;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public boolean create(Activity activity) {
		return activityRepo.save(activity) != null;
	}

	@Override
	public Activity findById(int id) {
		Optional<Activity> activity = activityRepo.findById(id);
		return activity.isPresent() ? activity.get() : null;
	}

	@Override
	public List<Activity> findAll() {
		return activityRepo.findAll();
	}

	@Override
	public boolean update(Activity activity) {
		return activityRepo.save(activity) != null;
	}

	@Override
	public void delete(Activity activity) {
		activityRepo.delete(activity);
	}

}
