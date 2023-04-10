package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    

    @Query(value = "SELECT DISTINCT * FROM user u INNER JOIN course c ON u.id = c.user_id GROUP BY u.id", nativeQuery = true)
    public List<User> getTutors(); 
    
    @Query(value = "SELECT u.id, u.description, u.email, u.password, u.pfp_url, u.username, avg(s.rating) as rating FROM user u INNER JOIN course c ON u.id = c.user_id LEFT JOIN session s ON c.id = s.course_id GROUP BY u.id", nativeQuery=true)
    public List<User> getTutorsRating();
    
    @Query(value="SELECT u.id, u.description, u.email, u.password, u.pfp_url, u.username, avg(s.rating) as rating as rating FROM user u INNER JOIN course c ON u.id = c.user_id LEFT JOIN session s ON c.id = s.course_id WHERE u.username = ?1 GROUP BY u.id", nativeQuery = true)
    public User getTutorRating(String username);
}
