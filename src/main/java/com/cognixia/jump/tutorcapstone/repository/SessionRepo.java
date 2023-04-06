package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session,Integer> {
	
	
	@Query(value = "SELECT AVG(s.rating) FROM session s LEFT JOIN course c ON s.course_id LEFT JOIN user u ON u.id WHERE u.username = ?1  GROUP BY u.username", nativeQuery = true)
	public Double tutorAvarage(String tutor);
	
}
