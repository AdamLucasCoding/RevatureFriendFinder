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
	
	public ActivityParticipantServiceImpl(ActivityParticipantRepository dao) {
		this.aprepo = dao;
	}

	@Override
	public boolean createAp(ActivityParticipant ap) {
		int pk = aprepo.save(ap).getId();
		return (pk > 0) ? true : false;
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
	public boolean updateAp(ActivityParticipant ap) {
		return (aprepo.save(ap).getId() > 0) ? true : false;
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

	@Override
	public ActivityParticipant getApById(int id) {
		ActivityParticipant tempAp = aprepo.findById(id).get();
		return tempAp;
	}

}
