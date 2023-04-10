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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.cognixia.jump.tutorcapstone.model.UserAndRating;
import com.cognixia.jump.tutorcapstone.service.UserAndRatingService;

@WebMvcTest(UserAndRatingController.class)
public class UserAndRatingControllerTest {

    private static final String STARTING_URI = "http://localhost:8080/api/user";
    private List<UserAndRating> urs = new ArrayList<>();
    private UserAndRating ur1 = new UserAndRating(1, "a", "", "", "", "", 5.0);
    private UserAndRating ur2 = new UserAndRating(2, "", "", "", "", "", 5.0);
    {
        urs.add(ur1);
        urs.add(ur2);
    }

    @Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserAndRatingService service;
	
	@InjectMocks
	private UserAndRatingController controller;

    @Test
    void testTutorsRating() throws Exception{
        when(service.getTutorsRating()).thenReturn(urs);
		String uri = STARTING_URI + "/tutorsRating";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).getTutorsRating();
		verifyNoMoreInteractions(service);
    }

    @Test
    void testGetTutorRating() throws Exception{
        when(service.getTutorRating(ur1.getUsername())).thenReturn(ur1);
		String uri = STARTING_URI + "/tutorRating/a";
		mvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(service, times(1)).getTutorRating(ur1.getUsername());
		verifyNoMoreInteractions(service);
    }
}
