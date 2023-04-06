package com.cognixia.jump.tutorcapstone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.repository.SessionRepo;

@Service
public class SessionService {

    @Autowired
    SessionRepo repo;

    public Session createSession(Session session){

        session.setId(null); 
        Session created = repo.save(session);
        return created;
    }

    public boolean deleteSession(int id) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			
			repo.deleteById(id);
			
			return true;
			
		}
		
		throw new ResourceNotFoundException("No such session exists" );
	}

    public List<Session> getSessionByStudent(int id){
        return repo.getSessions(id);
    }

    public List<Session> getSessionByTutor(int id){
        return repo.getSessionsForTutors(id);
    }

    
}
