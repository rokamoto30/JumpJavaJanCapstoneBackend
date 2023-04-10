//package com.cognixia.jump.tutorcapstone.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import com.cognixia.jump.tutorcapstone.model.UserAndRating;
//
//@Repository
//public interface UserAndRatingRepo extends JpaRepository<UserAndRating, Integer> {
//	 	@Query(value = "SELECT u.*, avg(s.rating) as rating FROM user u INNER JOIN course c ON u.id = c.user_id LEFT JOIN session s ON c.id = s.course_id GROUP BY u.id", nativeQuery=true)
//	    public List<UserAndRating> getTutorsRating();
//	    
//	    @Query(value="SELECT u.*, avg(s.rating) as rating FROM user u INNER JOIN course c ON u.id = c.user_id LEFT JOIN session s ON c.id = s.course_id WHERE u.username = ?1 GROUP BY u.id", nativeQuery = true)
//	    public UserAndRating getTutorRating(String username);
//}
