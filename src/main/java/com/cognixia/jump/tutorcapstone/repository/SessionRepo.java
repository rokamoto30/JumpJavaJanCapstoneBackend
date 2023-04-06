package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
	
	@Query("SELECT AVG(s.rating) FROM session s WHERE s.course = ?1 GROUP BY s.course")
	public Double courseAvarage(String course);
	
	@Query("SELECT AVG(s.rating) FROM session s LEFT JOIN course c WHERE c.tutor = ?1 GROUP BY c.tutor")
	public Double tutorAvarage(String tutor);
	
	

}
