package com.spring.security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String SayHello() {
		return "Hello...";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String SayHelloUser() {
		return "Hello...User!";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String SayHelloAdmin() {
		return "Hello...Admin!";
	}


}
