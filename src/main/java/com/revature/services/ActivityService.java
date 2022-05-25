package com.revature.services;

import java.util.List;
import java.util.Optional;
import com.revature.models.Activity;

public interface ActivityService {

	boolean create(Activity activity);

	Optional<Activity> findById(int id);

	List<Activity> findAll();

	boolean update(Activity activity);

	void delete(Activity activity);

}
