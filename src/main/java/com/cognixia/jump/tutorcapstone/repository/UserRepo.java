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
    
<<<<<<< HEAD
    @Query(value = "SELECT u.id, u.description, u.email, u.pfp_url, u.password, u.username FROM user u INNER JOIN course c ON u.id = c.user_id", nativeQuery = true)
=======
    @Query(value = "SELECT * FROM user u INNER JOIN course c ON u.id = c.user_id", nativeQuery = true)
>>>>>>> a936792c287dfcd3c0c4ac009e1b0d255d38c9d9
    public List<User> getTutors();

}
