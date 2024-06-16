package com.booking.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.airline.model.LoginDetails;
import com.booking.airline.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> createLoginDetails(@RequestBody LoginDetails loginDetails){
		
		return loginService.createLoginDetails(loginDetails);
		
	}
	@PostMapping("/login")
	public ResponseEntity<String> checkUserLogin(@RequestBody LoginDetails loginDetails){
		
		return loginService.checkUserLogin(loginDetails);
		
	}
}
