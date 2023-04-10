package com.cognixia.jump.tutorcapstone.service;

import java.util.List;
import java.util.Optional;

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

    public List<User> getUsers() {
		
		return repo.findAll();
	}
    
    public List<User> getTutorsRating() {
		return repo.getTutorsRating();
	}
	    
	public User getTutorRating(String username) {
		return repo.getTutorRating(username);
	}
    
    public List<User> getTutors() {
    	return repo.getTutors();
    }
    

    public User getUserById(int id) throws ResourceNotFoundException {
		
		Optional<User> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("No such user exists");
		}
		
		return found.get();
	}

	public  User getUserByUsername(String username) throws ResourceNotFoundException{
		Optional<User> found=repo.findByUsername(username);

		if(found.isEmpty()) {
			throw new ResourceNotFoundException("No such user exists");
		}

		return found.get();
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
