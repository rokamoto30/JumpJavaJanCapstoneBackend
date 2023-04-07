package com.cognixia.jump.tutorcapstone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.tutorcapstone.exception.ResourceNotFoundException;
import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.repository.CourseRepo;

@Service
public class CourseService {
	@Autowired
	CourseRepo repo; 
	 
	public Course createCourse(Course newCourse){
		newCourse.setId(null);
	    Course created = repo.save(newCourse);
	    return created;
	}
	
	public Course updateCourse(Course course) throws ResourceNotFoundException {
		if (repo.existsById(course.getId())) {
			Course updated = repo.save(course);
			return updated;
		}
		throw new ResourceNotFoundException( "No such course exists" );
	}
	
	public boolean deleteCourse(int id) throws ResourceNotFoundException {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		throw new ResourceNotFoundException( "No such course exists" );
	}
	
	
	public List<Course> findBySubject(String subject) {
		return repo.findBySubject(subject);
	}
	public List<Course> findByUserId(Integer id) {
		return repo.findByUserId(id);
	}
	
	public List<Course> getCourses() {
		return repo.findAll();
	}
	 
	 
}
