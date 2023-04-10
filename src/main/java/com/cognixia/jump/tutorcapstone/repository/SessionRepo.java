package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.Session;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
	
	
	@Query(value = "SELECT AVG(s.rating) FROM session s LEFT JOIN course c ON s.course_id LEFT JOIN user u ON u.id WHERE u.username = ?1  GROUP BY u.username", nativeQuery = true)
	public Double tutorAvarage(String tutor);


	//For students
	@Query(value = "SELECT s.id, s.end, s.rating, s.start, s.course_id, s.user_id, ((TIMESTAMPDIFF(minute, s.start, s.end)*c.hourly)/60) AS cost FROM session s LEFT JOIN course c ON s.course_id = c.id WHERE s.user_id = ?1", nativeQuery = true)
	public List<Session> getSessions(int id);
	
	//For tutors
	@Query(value = "SELECT s.id, s.end, s.rating, s.start, s.course_id, s.user_id, ((TIMESTAMPDIFF(minute, s.start, s.end)*c.hourly)/60) AS cost FROM session s JOIN course c ON s.course_id = c.id WHERE c.user_id = ?1", nativeQuery = true)
	public List<Session> getSessionsForTutors(int id);
	
	
	@Query(value = "SELECT c.hourly FROM session s LEFT JOIN course c ON s.course_id = c.id WHERE s.id = 1;", nativeQuery = true)
	public Double cost(int id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE session s SET s.rating = ?1 WHERE s.id = ?2", nativeQuery = true)
	public int updateRating(Double rating, int id);
	
}
