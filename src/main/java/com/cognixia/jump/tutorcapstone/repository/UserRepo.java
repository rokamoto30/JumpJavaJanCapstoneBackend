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
    
    @Query(value = "SELECT * FROM user u INNER JOIN course c ON u.id = c.user_id", nativeQuery = true)
    public List<User> getTutors();

}
