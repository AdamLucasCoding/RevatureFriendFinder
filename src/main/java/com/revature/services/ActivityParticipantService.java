package com.revature.services;

import java.util.List;

import com.revature.models.ActivityParticipant;

public interface ActivityParticipantService {

	boolean createAp(ActivityParticipant ap);

	List<ActivityParticipant> getApByActivity(int activity_id);

	List<ActivityParticipant> getApByUser(int user_id);

	boolean updateAp(ActivityParticipant ap);

	boolean deleteAp(ActivityParticipant ap);

	List<ActivityParticipant> getAllAp();

	ActivityParticipant getApById(int id);

}
