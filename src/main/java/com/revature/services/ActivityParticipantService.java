package com.revature.services;

import java.util.List;

import com.revature.models.ActivityParticipant;

public interface ActivityParticipantService {

	boolean create(ActivityParticipant activityParticipant);

	ActivityParticipant findById(int id);

	List<ActivityParticipant> findAllParticipants(int activityId);

	List<ActivityParticipant> findAllActivities(int participantId);

	boolean update(ActivityParticipant activityParticipant);

	void delete(ActivityParticipant activityParticipant);

}
