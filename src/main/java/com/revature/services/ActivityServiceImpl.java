package com.revature.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;


import com.revature.models.Activity;
import com.revature.repositories.ActivityRepository;
import com.revature.util.CalendarQuickstart;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository arepo;
	
	@Override
	public List<Activity> getAllActivities() {
		List<Activity> allActivities = arepo.findAll();
		return allActivities;
	}

	@Override
	public Activity getActivityById(int id) {
		Activity tempActivity = arepo.findById(id).get();
		return tempActivity;
		//return arepo.findById(id).stream().findFirst().get();
	}

	@Override
	public Activity createActivity(Activity activity) throws IOException, GeneralSecurityException {
		Activity temp = arepo.save(activity);
		Event event = new Event();
		
		if (temp != null) {
			Credential credentials = CalendarQuickstart.getCredentials(GoogleNetHttpTransport.newTrustedTransport(), temp.getName());
			event = new Event().setSummary(temp.getName()).setLocation(temp.getLocation());
			
			DateTime startDateTime = temp.getDateTime();
			EventDateTime start = new EventDateTime()
			    .setDateTime(startDateTime)
			    .setTimeZone("America/New_York");
			
			event.setStart(start);
			
			String calendarId = "primary";
			Calendar service = CalendarQuickstart.APIStart(temp.getCreated_by().getEmail());
			event = service.events().insert(calendarId, event).execute();
			System.out.printf("Event created: %s\n", event.getHtmlLink());
		}
		
		return temp;
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
		List<Activity> activitiesByType = arepo.findByType(type);
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
