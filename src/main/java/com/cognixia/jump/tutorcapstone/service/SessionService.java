package com.cognixia.jump.tutorcapstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.repository.SessionRepo;

@Service
public class SessionService {

    @Autowired
    SessionRepo repo;
    
    public Session getById(int id) throws ResourceNotFoundException {
    	Optional<Session> found = repo.findById(id);
    	if (found.isEmpty()) {
    		throw new ResourceNotFoundException("No such session exists");
    	}
    	return found.get();
    }

    public Session createSession(Session session){

        session.setId(null); 
        Session created = repo.save(session);
        return created;
    }
    
    public Double getCost(int id) {
    	return repo.cost(id);
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
    
    public Session updateRating(Double rating, int id) throws ResourceNotFoundException {
		
		int count = repo.updateRating(rating, id);
		
		if( count > 0 ) {
			return getById(id);
		}
		
		throw new ResourceNotFoundException("No such session exists");
	}

    
}
