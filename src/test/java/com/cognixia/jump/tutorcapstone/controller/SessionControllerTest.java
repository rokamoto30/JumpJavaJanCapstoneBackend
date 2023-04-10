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
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.service.SessionService;

@WebMvcTest(SessionController.class)
public class SessionControllerTest {
    private static final String STARTING_URI = "http://localhost:8080/api";
    
        private List<Session> allSessions = new ArrayList<Session>();
        private Session s1 = new Session();
        private Session s2 = new Session();
    {
        allSessions.add(s1);
        allSessions.add(s2);
    }
    @Autowired
	private MockMvc mvc;

    @MockBean
	private SessionService service;
	
	@InjectMocks
	private SessionController controller;

    @Test
    void testCreateUser() throws Exception{
        when(service.createSession(s1)).thenReturn(s1);
		String uri = STARTING_URI + "/session";
		mvc.perform(post(STARTING_URI))
		.andDo(print())
		.andExpect(status().isCreated());
		
    }

    @Test
    void testGetStudentSessions() throws Exception{
        when(service.getSessionByStudent(s1.getId())).thenReturn(allSessions);
        String uri = STARTING_URI + "/student/session/1";
        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getSessionByStudent(1);
		verifyNoMoreInteractions(service);
    }

    @Test
    void testGetCost() throws Exception{
        when(service.getCost(s1.getId())).thenReturn(37.0);
        String uri = STARTING_URI + "/student/session/cost/1";
        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getCost(1);
		verifyNoMoreInteractions(service);
    }

    @Test
    void testGetTutorSessions() throws Exception{
        when(service.getSessionByTutor(s1.getId())).thenReturn(allSessions);
        String uri = STARTING_URI + "/tutor/session/1";
        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getSessionByTutor(1);
		verifyNoMoreInteractions(service);
    }

    @Test
    void testDeleteSession() throws Exception{
        when(service.deleteSession(s1.getId())).thenReturn(true);
        String uri = STARTING_URI + "/session/1";
        mvc.perform(delete(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());
    }

}
