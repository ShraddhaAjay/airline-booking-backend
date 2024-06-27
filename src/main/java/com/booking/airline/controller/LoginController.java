package com.booking.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.airline.model.Login;
import com.booking.airline.service.LoginService;

@CrossOrigin(originPatterns = "*")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> createLoginDetails(@RequestBody Login login){
		
		return loginService.createLoginDetails(login);
		
	}
	@PostMapping("/login")
	public ResponseEntity<Login> checkUserLogin(@RequestBody Login login){
		
		return loginService.checkUserLogin(login);
		
	}
	@PutMapping("/passwordReset")
	public ResponseEntity<String>passwordReset(@RequestBody Login login){
		return loginService.passwordReset(login);
	}
}
