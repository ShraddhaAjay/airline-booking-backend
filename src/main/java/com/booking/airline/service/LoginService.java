package com.booking.airline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booking.airline.model.LoginDetails;
import com.booking.airline.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public ResponseEntity<String> createLoginDetails(LoginDetails loginDetails) {

		Optional<LoginDetails> existing = loginRepository.findById(loginDetails.getUsername());
		if (existing.isEmpty()) {
			loginDetails.setAccountStatus("Active");
			loginDetails.setIsAdmin("N");
			loginRepository.save(loginDetails);
			return new ResponseEntity("User created successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> checkUserLogin(LoginDetails loginDetails) {

		Optional<LoginDetails> existing = loginRepository.findById(loginDetails.getUsername());
		if (existing.isPresent()) {
			if (loginDetails.getPassword().equals(existing.get().getPassword())) {
				return new ResponseEntity("User login success", HttpStatus.OK);
			}
			return new ResponseEntity("User login failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Username invalid", HttpStatus.BAD_REQUEST);
	}
}
