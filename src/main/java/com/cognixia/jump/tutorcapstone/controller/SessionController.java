package com.cognixia.jump.tutorcapstone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.service.SessionService;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
	SessionService service;

    @PostMapping("/session")
	public ResponseEntity<?> createUser(@Valid @RequestBody Session session) {
		
		Session created = service.createSession(session);
		
		return ResponseEntity.status(201).body(created);
	}

    @GetMapping("/student/session/{id}")
	public List<Session> getStudentSessions(@PathVariable int id) {
		return service.getSessionByStudent(id);
	}
    
    @GetMapping("/session/{id}")
	public Session getSessionById(@PathVariable int id) throws ResourceNotFoundException {
		return service.getById(id);
	}
    
    @PatchMapping("/session/rating/{id}/{rating}")
    public ResponseEntity<?> updateMajor(@PathVariable int id, @PathVariable Double rating) throws ResourceNotFoundException {
		
		Session updated = service.updateRating(rating, id);
		
		return ResponseEntity.status(200)
							 .body(updated);
	}
    
    @GetMapping("/student/session/cost/{sessionId}")
	public Double getCost(@PathVariable int sessionId) {
		return service.getCost(sessionId);
	}

    @GetMapping("/tutor/session/{id}")
	public List<Session> getTutorSessions(@PathVariable int id) {
		return service.getSessionByTutor(id);
	}

    @DeleteMapping("/session/{id}")
	public ResponseEntity<?> deleteSession(@PathVariable int id) throws ResourceNotFoundException {
		
		service.deleteSession(id);
			
		return ResponseEntity.status(200)
							 .body("Session Deleted");
	
	}
}
