package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Activity;

@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	@Query(value = "SELECT * FROM activities WHERE type = :type",
			nativeQuery = true)
	public List<Activity> findByType(@Param("type") String type);

	@Query(value = "SELECT * FROM activities WHERE location = :location",
			nativeQuery = true)
	public List<Activity> findByLocation(@Param("location") String location);

	@Query(value = "SELECT * FROM activities WHERE created_by = :created_by",
			nativeQuery = true)
	public List<Activity> findByCreator(@Param("created_by") int created_by);

}
