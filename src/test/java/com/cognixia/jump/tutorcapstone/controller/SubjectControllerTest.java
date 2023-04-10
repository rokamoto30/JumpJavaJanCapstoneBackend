package com.cognixia.jump.tutorcapstone.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.model.Subject;
import com.cognixia.jump.tutorcapstone.repository.SubjectRepo;
import com.cognixia.jump.tutorcapstone.service.MyUserDetailsService;
import com.cognixia.jump.tutorcapstone.util.JwtUtil;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    private static final String STARTING_URI = "http://localhost:8080/api/subject";
    private List<Subject> subs = new ArrayList<>();
    private Subject s1 = new Subject(1, "Math", "Math", "a", new ArrayList<Course>());
    private Subject s2 = new Subject(2, "Sci", "Sci", "a", new ArrayList<Course>());
    {
        subs.add(s1);
        subs.add(s2);
    }

    @Autowired
	private MockMvc mvc;
    
    @MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@MockBean
	private SubjectRepo repo;
	
	@InjectMocks
	private SubjectController controller;

    @Test
    @WithMockUser(username="user",roles="USER")
    void testGetSubjects() throws Exception{
        when(repo.findAll()).thenReturn(subs);

        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(repo, times(1)).findAll();
		verifyNoMoreInteractions(repo);
    }

}
