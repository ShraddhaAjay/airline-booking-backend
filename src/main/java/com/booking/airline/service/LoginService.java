package com.booking.airline.service;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booking.airline.model.Login;
import com.booking.airline.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public ResponseEntity<String> createLoginDetails(Login login) {//username,pas,stat,admin

		Optional<Login> existing = loginRepository.findById(login.getUsername());//client
		if (existing.isEmpty()) {
			if(Strings.isBlank(login.getPassword())){
				return new ResponseEntity("Password can not be blank", HttpStatus.BAD_REQUEST);
			}
			login.setAccountStatus("Active");
			login.setIsAdmin("N");
			loginRepository.save(login);
			return new ResponseEntity("User created successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> checkUserLogin(Login login) {

		Optional<Login> existing = loginRepository.findById(login.getUsername());
		if (existing.isPresent()) {
			if (login.getPassword().equals(existing.get().getPassword())) {
				return new ResponseEntity("User login success", HttpStatus.OK);
			}
			return new ResponseEntity("User login failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Username invalid", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> passwordReset(Login login) {
		Optional<Login> existing = loginRepository.findById(login.getUsername());
		if(existing.isPresent()){
			if(Strings.isBlank(login.getPassword())){
				return new ResponseEntity("Password can not be blank", HttpStatus.BAD_REQUEST);
			}
			Login existingPassword = existing.get();
			existingPassword.setPassword(login.getPassword());
			loginRepository.save(existingPassword);
			return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("UserName Invalid", HttpStatus.BAD_REQUEST);
	}
}
