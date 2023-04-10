package com.cognixia.jump.tutorcapstone.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.model.Subject;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.service.CourseService;
import com.cognixia.jump.tutorcapstone.service.MyUserDetailsService;
import com.cognixia.jump.tutorcapstone.util.JwtUtil;


@WebMvcTest(CourseController.class)
public class CourseControllerTest {
	
	private static final String STARTING_URI = "http://localhost:8080/api/course";
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
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@InjectMocks
	private CourseController controller;
	
	@Test
	@WithMockUser(username="user",roles="USER")
	void testGetCourse() throws Exception {		
		when(service.getCourses()).thenReturn(allCourses);
		
		mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).getCourses();
		verifyNoMoreInteractions(service);
	}
	
	@Test
	@WithMockUser(username="user",roles="USER")
	void testGetCourseBySubject() throws Exception {
		when(service.findBySubject("Math")).thenReturn(allCourses);
		String uri = STARTING_URI + "/subject/Math";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).findBySubject("Math");
		verifyNoMoreInteractions(service);
	}
	
	@Test
	@WithMockUser(username="user",roles="USER")
	void getCourseByTutor() throws Exception {
		when(service.findByUserId(1)).thenReturn(allCourses);
		String uri = STARTING_URI + "/user_id/1";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).findByUserId(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	@WithMockUser(username="user",roles="USER")
	void testPostCourse() throws Exception{
		String uri = STARTING_URI + "/create";
		Course tempCourse = new Course(1,  new Subject(), new User(), new ArrayList<Session>(), "Now", 12.00);
		when(service.createCourse(Mockito.any(Course.class))).thenReturn(tempCourse);
		mvc.perform(post(uri))
		.andDo(print())
		.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(username="user",roles="USER")
	void testUpdateStudent() throws Exception{
		when(service.updateCourse(Mockito.any(Course.class))).thenReturn(course1);
		String uri = STARTING_URI + "/update";
		mvc.perform(put(uri))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username="user",roles="USER")
	void testDeleteStudent() throws Exception{
		when(service.deleteCourse(course1.getId())).thenReturn(true);
		String uri = STARTING_URI + "/delete/1";
		mvc.perform(delete(uri))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
