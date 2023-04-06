package com.cognixia.jump.tutorcapstone.repository;

import com.cognixia.jump.tutorcapstone.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Integer> {

}
