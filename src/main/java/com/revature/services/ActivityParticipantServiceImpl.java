package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.ActivityParticipant;
import com.revature.repositories.ActivityParticipantRepository;

@Service
@Transactional
public class ActivityParticipantServiceImpl implements ActivityParticipantService {

	private ActivityParticipantRepository repo;
	
	@Override
	public boolean create(ActivityParticipant activityParticipant) {		
		return repo.save(activityParticipant) != null;
	}

	@Override
	public ActivityParticipant findById(int id) {
		Optional<ActivityParticipant>  activityParticipant = repo.findById(id);
		return activityParticipant.isPresent() ? activityParticipant.get() : null;
	}

	@Override
	public List<ActivityParticipant> findAllParticipants(int activityId) {
//		repo.findAll().
		return null;
	}

	@Override
	public List<ActivityParticipant> findAllActivities(int participantId) {
		
		return null;
	}

	@Override
	public boolean update(ActivityParticipant activityParticipant) {		
		return repo.save(activityParticipant) != null;
	}

	@Override
	public void delete(ActivityParticipant activityParticipant) {
		repo.delete(activityParticipant);

	}

}
