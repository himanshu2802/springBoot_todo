package com.in28minutes.rest.webservices.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.jwt.auth.MyUserDetailsService;
import com.in28minutes.rest.webservices.jwt.models.AuthenticationRequest;
import com.in28minutes.rest.webservices.jwt.models.AuthenticationResponse;
import com.in28minutes.rest.webservices.util.JwtUtil;

@CrossOrigin("http://localhost:4200")
@RestController
public class JwtAuthenticationResource {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest body) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(body.getUserName(), body.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect User Name or Passwprd", e);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(body.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}

}
