package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.ActivityParticipant;

@Repository
@Transactional
public interface ActivityParticipantRepository extends JpaRepository<ActivityParticipant, Integer> {

}
