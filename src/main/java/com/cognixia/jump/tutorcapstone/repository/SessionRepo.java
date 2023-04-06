package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.Session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
	
	
	@Query(value = "SELECT AVG(s.rating) FROM session s LEFT JOIN course c ON s.course_id LEFT JOIN user u ON u.id WHERE u.username = ?1  GROUP BY u.username", nativeQuery = true)
	public Double tutorAvarage(String tutor);


	//For students
	@Query("SELECT * FROM session s WHERE s.user_id = ?1")
	public List<Session> getSessions(int id);
	
	//For tutors
	@Query("SELECT s.id, s.end, s.rating, s.start, s.course_id, s.user_id FROM session s JOIN course c ON s.course_id = c.id WHERE c.user_id = ?1")
	public List<Session> getSessionsForTutors(int id);

}
