package com.cognixia.jump.tutorcapstone.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.service.CourseService;

@RequestMapping("/api/course")
@RestController
public class CourseController {
	@Autowired
	CourseService service;
	
	@GetMapping()
	public List<Course> getCourse() {
		return service.findAll();
	}
	@GetMapping("subject/{subject}")
	public List<Course> getCourseBySubject(@PathVariable String subject) {
		return service.findBySubject(subject);
	}
	@GetMapping("user_id/{user_id}")
	public List<Course> getCourseByTutor(@PathVariable Integer user_id) {
		return service.findByUserId(user_id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
		
		Course created = service.createCourse(course);
		
		return ResponseEntity.status(201).body(created);
	} 
	
	@PutMapping("/update")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Course course) throws ResourceNotFoundException {
		
		Course updated = service.updateCourse(course);
		
		return ResponseEntity.status(200)
							 .body(updated);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) throws ResourceNotFoundException {
		
		service.deleteCourse(id);
			
		return ResponseEntity.status(200)
							 .body("Deleted Student with id = " + id);
	
	}
	
}
