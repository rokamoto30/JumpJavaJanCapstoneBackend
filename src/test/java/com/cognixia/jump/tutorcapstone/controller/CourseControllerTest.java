package com.cognixia.jump.tutorcapstone.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.model.Subject;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.service.CourseService;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	private static final String STARTING_URI = "http://localhost:8080//api/course";
	private List<Course> allCourses = new ArrayList<Course>();
	private Course course1 = new Course(1,  new Subject(), new User(), new ArrayList<Session>(), "Now", 12.00);
	private Course course2 = new Course(2,  new Subject(), new User(), new ArrayList<Session>(), "Now", 12.00);
	private Course course3 = new Course(3,  new Subject(), new User(), new ArrayList<Session>(), "Now", 12.00);
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CourseService service;
	
	@Test
	void testGetCourse() throws Exception {
		List<Course> allCourses = new ArrayList<Course>();
		
		
	}
}
