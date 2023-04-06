package com.cognixia.jump.tutorcapstone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/user")
    public List<User> getUsers() {
		
		return service.getUsers();
	}

    @GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) throws ResourceNotFoundException {
		
		User found = service.getUserById(id);
		
		return ResponseEntity.status(200).body( found );
		
	}

    @PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		
		User created = service.createUser(user);
		
		return ResponseEntity.status(201).body(created);
	}

    @PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
		
		User updated = service.updateUser(user);
		
		return ResponseEntity.status(200)
							 .body(updated);
		
	}

    @DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
		
		service.deleteUser(id);
			
		return ResponseEntity.status(200)
							 .body("Deleted Student with id = " + id);
	
	}
	
    
}