package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Message;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query(value = "SELECT * FROM messages WHERE author_id = :author_id",
			nativeQuery = true)
	List<Message> findByUser(int author_id);

	@Query(value = "SELECT * FROM messages WHERE activity_id = :activity_id",
			nativeQuery = true)
	List<Message> findByActivity(int activity_id);

}
