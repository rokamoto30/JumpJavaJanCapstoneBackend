package com.cognixia.jump.tutorcapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.model.AuthenticationRequest;
import com.cognixia.jump.tutorcapstone.model.AuthenticationResponse;
import com.cognixia.jump.tutorcapstone.util.JwtUtil;


@RestController
public class AuthenticationController {

	// authentication manager -> validates/authenticates user credentials
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;
	
	
	// create the token at http://localhost:8080/authenticate 
	// send the username & password and try to generate a token as a response
	@PostMapping("/authenticate")
	public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
		
		// try to catch the exception for bad credentials, just so we can set our own
		// message when this doesn't work
		try {
			// make sure we have a valid user by checking their username and password
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		} catch (BadCredentialsException e) {
			// provide our own message on why login didn't work
			throw new Exception("Incorrect username or password");
		}

		// as long as no exception was thrown, user is valid

		// load in the user details for that user
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		// generate the token for that user
		final String jwt = jwtUtil.generateTokens(userDetails);

		// return the token
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );

	}
	
}


/*
package com.cognixia.jump.tutorcapstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.tutorcapstone.dto.AuthCredentialsRequest;
import com.cognixia.jump.tutorcapstone.model.AuthenticationRequest;
import com.cognixia.jump.tutorcapstone.model.AuthenticationResponse;
import com.cognixia.jump.tutorcapstone.model.User;
import com.cognixia.jump.tutorcapstone.util.JwtUtil;


@RestController
@RequestMapping("/api/auth/login")
public class AuthenticationController {

	// authentication manager -> validates/authenticates user credentials
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	// create the token at http://localhost:8080/authenticate 
	// send the username & password and try to generate a token as a response
	@PostMapping("/authenticate")
	public ResponseEntity<?> createJwtToken(@RequestBody AuthCredentialsRequest request) throws Exception {
		
		// try to catch the exception for bad credentials, just so we can set our own
		// message when this doesn't work
		try {
			// make sure we have a valid user by checking their username and password
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
			User user = (User) authenticate.getPrincipal();
			user.setPassword(null);
			
			return ResponseEntity.ok()
					.header(
					HttpHeaders.AUTHORIZATION, 
					jwtUtil.generateToken(user)
					
					)
					.body(user);

		} catch (BadCredentialsException e) {
			// provide our own message on why login didn't work
			throw new Exception("Incorrect username or password");
		}

		// as long as no exception was thrown, user is valid

		// load in the user details for that user
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		// generate the token for that user
		final String jwt = jwtUtil.generateTokens(userDetails);

		// return the token
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );

	}
	
}
*/