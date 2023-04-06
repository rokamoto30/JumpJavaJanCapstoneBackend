package com.cognixia.jump.tutorcapstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    UserRepo repo;

    public User createUser(User user){

        user.setId(null);
        User created = repo.save(user);
        return created;
    }

    public User updateUser(User user) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(user.getId());
		
		if(exists) {
		
			User updated = repo.save(user);
			
			return updated;
			
		}
		
		throw new ResourceNotFoundException( "No such user exists" );
	}

    public boolean deleteUser(int id) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			
			repo.deleteById(id);
			
			return true;
			
		}
		
		throw new ResourceNotFoundException("No such user exists" );
	}

}
