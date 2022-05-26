package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.ActivityParticipant;

@Repository
@Transactional
public interface ActivityParticipantRepository extends JpaRepository<ActivityParticipant, Integer> {

	@Query(value = "SELECT * FROM activity_participants WHERE activity_id = :activity_id")
	List<ActivityParticipant> findByActivity(int activity_id);
	
	@Query(value = "SELECT * FROM activity_participants WHERE user_id = :user_id")
	List<ActivityParticipant> findByUser(int user_id);

}
