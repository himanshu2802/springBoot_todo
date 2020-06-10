package com.in28minutes.rest.webservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
public class BasicAuthenticationController {
	
	@GetMapping("basicauth")
	public AuthenticationBean helloWorldBean() {
//		throw new RuntimeException("Some Error has happenned..");
		return new AuthenticationBean("You are Authenticated");
	}
	
}
