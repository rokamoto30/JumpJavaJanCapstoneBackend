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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class SessionController {

    @Autowired
	SessionService service;

    @PostMapping("/session")
    @Operation(summary="create a session")
	public ResponseEntity<?> createUser(@Valid @RequestBody Session session) {
		
		Session created = service.createSession(session);
		
		return ResponseEntity.status(201).body(created);
	}

    @GetMapping("/student/session/{id}")
    @Operation(summary="get sessions by student", description="params: user id")
	public List<Session> getStudentSessions(@PathVariable int id) {
		return service.getSessionByStudent(id);
	}
    
    @GetMapping("/session/{id}")
    @Operation(summary="get session by id", description="params: session id")
	public Session getSessionById(@PathVariable int id) throws ResourceNotFoundException {
		return service.getById(id);
	}
    
    @PatchMapping("/session/rating/{id}/{rating}")
    @Operation(summary="update rating", description="params: session id, new rating")
    public ResponseEntity<?> updateMajor(@PathVariable int id, @PathVariable Double rating) throws ResourceNotFoundException {
		
		Session updated = service.updateRating(rating, id);
		
		return ResponseEntity.status(200)
							 .body(updated);
	}
    
    @GetMapping("/student/session/cost/{sessionId}")
    @Operation(summary="get session cost", description="params: session id")
	public Double getCost(@PathVariable int sessionId) {
		return service.getCost(sessionId);
	}

    @GetMapping("/tutor/session/{id}")
    @Operation(summary="get sesion by tutor", description="params: tutor user id")
	public List<Session> getTutorSessions(@PathVariable int id) {
		return service.getSessionByTutor(id);
	}

    @DeleteMapping("/session/{id}")
    @Operation(summary="delete session", description="params: session id")
	public ResponseEntity<?> deleteSession(@PathVariable int id) throws ResourceNotFoundException {
		
		service.deleteSession(id);
			
		return ResponseEntity.status(200)
							 .body("Session Deleted");
	
	}
}
