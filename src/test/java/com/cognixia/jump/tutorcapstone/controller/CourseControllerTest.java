package com.cognixia.jump.tutorcapstone.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
	{
		course1.getSubject().setName("Math");
		course1.getSubject().setName("Math");
		course1.getSubject().setName("Math");
		allCourses.add(course1);
		allCourses.add(course2);
		allCourses.add(course3);
	}
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CourseService service;
	
	@InjectMocks
	private CourseController controller;
	
	@Test
	void testGetCourse() throws Exception {		
		when(service.findAll()).thenReturn(allCourses);
		
		mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).findAll();
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testGetCourseBySubject() throws Exception {
		when(service.findBySubject("Math")).thenReturn(allCourses);
		String uri = STARTING_URI + "subject/{Math}";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).findAll();
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void getCourseByTutor() throws Exception {
		when(service.findByUserId(1)).thenReturn(allCourses);
		String uri = STARTING_URI + "user_id/{1}";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).findAll();
		verifyNoMoreInteractions(service);
	}
}
