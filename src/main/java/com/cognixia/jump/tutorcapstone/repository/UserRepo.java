package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
