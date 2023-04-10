//package com.cognixia.jump.tutorcapstone.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cognixia.jump.tutorcapstone.model.UserAndRating;
//import com.cognixia.jump.tutorcapstone.service.UserAndRatingService;
//
//@RestController
//@RequestMapping("/api")
//public class UserAndRatingController {
//	
//	@Autowired
//    UserAndRatingService service;
//	
//	@GetMapping("/user/tutorsRating")
//    public List<UserAndRating> tutorsRating() {
//		
//		return service.getTutorsRating();
//	}
//    
//    @GetMapping("/user/tutorRating/{username}")
//    public UserAndRating getTutorRating(@PathVariable String username) {
//		return service.getTutorRating(username);
//	}
//
//}
