package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.ActivityParticipant;
import com.revature.repositories.ActivityParticipantRepository;

@Service
@Transactional
public class ActivityParticipantServiceImpl implements ActivityParticipantService {

	@Autowired
	private ActivityParticipantRepository aprepo;
	
	@Override
	public ActivityParticipant createAp(ActivityParticipant ap) {
		return aprepo.save(ap);
	}

	@Override
	public List<ActivityParticipant> getApByActivity(int activity_id) {
		return aprepo.findByActivity(activity_id);
	}

	@Override
	public List<ActivityParticipant> getApByUser(int user_id) {
		return aprepo.findByUser(user_id);
	}

	@Override
	public ActivityParticipant updateAp(ActivityParticipant ap) {
		return aprepo.save(ap);
	}

	@Override
	public boolean deleteAp(ActivityParticipant ap) {
		aprepo.delete(ap);
		return true;
	}

	@Override
	public List<ActivityParticipant> getAllAp() {
		return aprepo.findAll();
	}

}
