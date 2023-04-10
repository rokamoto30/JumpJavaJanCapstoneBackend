package com.cognixia.jump.tutorcapstone.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.tutorcapstone.model.Course;
import com.cognixia.jump.tutorcapstone.model.Session;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.service.MyUserDetailsService;
import com.cognixia.jump.tutorcapstone.service.UserService;
import com.cognixia.jump.tutorcapstone.util.JwtUtil;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    private static final String STARTING_URI = "http://localhost:8080/api/user";
    private List<User> users = new ArrayList<>();
    private User u1 = new User(1, "un", "pw", "p@em.com", "me", "tbd", "5.0", new ArrayList<Course>(), new ArrayList<Session>());
    private User u2 = new User(2, "un2", "pw", "p2@em.com", "me", "tbd", "5.0", new ArrayList<Course>(), new ArrayList<Session>());

    {
        users.add(u1);
        users.add(u2);
    }

    @Autowired
	private MockMvc mvc;
    
    @MockBean
	private UserService service;
    
    @MockBean
    private BCryptPasswordEncoder encoder;
    
    @MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	@InjectMocks
	private UserController controller;

    @Test
    @WithMockUser(username="user",roles="USER")
    void testGetUsers() throws Exception{
        when(service.getUsers()).thenReturn(users);
        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getUsers();
		verifyNoMoreInteractions(service);
    }

    @Test
    @WithMockUser(username="user",roles="USER")
    void testGetTutors() throws Exception{
        when(service.getTutors()).thenReturn(users);
        String uri = STARTING_URI + "/tutors";
        mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getTutors();
		verifyNoMoreInteractions(service);
    }

    @Test
    @WithMockUser(username="user",roles="USER")
    void testGetUserByUsername() throws Exception{
        when(service.getUserByUsername(u1.getUsername())).thenReturn(u1);
        String uri = STARTING_URI + "/name/un";
        mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getUserByUsername(u1.getUsername());
		verifyNoMoreInteractions(service);
    }

    @Test
    @WithMockUser(username="user",roles="USER")
    void testCreateUser() throws Exception{
        when(service.createUser(u1)).thenReturn(u1);
        mvc.perform(post(STARTING_URI))
		.andDo(print())
		.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="user",roles="USER")
    void testUpdate() throws Exception{
        when(service.updateUser(u1)).thenReturn(u1);
        mvc.perform(put(STARTING_URI))
		.andDo(print())
		.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="user",roles="USER")
    void testDeleteUser() throws Exception{
        when(service.deleteUser(u1.getId())).thenReturn(true);
        String uri = STARTING_URI + "/1";
        mvc.perform(delete(uri))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).deleteUser(u1.getId());
		verifyNoMoreInteractions(service);
    }


}
