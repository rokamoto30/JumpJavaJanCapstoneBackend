package com.cognixia.jump.tutorcapstone.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    private static final String STARTING_URI = "http://localhost:8080/api/user";
    private List<User> users = new ArrayList<>();
    private User u1 = new User(null, "un", "pw", "p@em.com", "me", "tbd");
    private User u2 = new User(null, "un2", "pw", "p2@em.com", "me", "tbd");

    {
        users.add(u1);
        users.add(u2);
    }

    @Autowired
	private MockMvc mvc;

    @MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;

    @Test
    void testGetUsers() throws Exception{
        when(service.getUsers()).thenReturn(users);
        mvc.perform(get(STARTING_URI))
		.andDo(print())
		.andExpect(status().isOk());

        verify(service, times(1)).getUsers();
		verifyNoMoreInteractions(service);
    }

    @Test
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
    void testCreateUser() throws Exception{
        when(service.createUser(u1)).thenReturn(u1);
        mvc.perform(post(STARTING_URI))
		.andDo(print())
		.andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception{
        when(service.updateUser(u1)).thenReturn(u1);
        mvc.perform(put(STARTING_URI))
		.andDo(print())
		.andExpect(status().isCreated());
    }

    @Test
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
