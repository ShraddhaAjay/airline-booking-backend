package com.booking.airline.service;

import java.util.Optional;

import com.booking.airline.model.PasswordDetails;
import com.booking.airline.model.User;
import com.booking.airline.repository.UserRepository;
import com.booking.airline.util.EncryptionUtil;
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
	private EncryptionUtil encryptionUtil;

	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> createLoginDetails(Login login) {//username,pas,stat,admin

		Optional<Login> existing = loginRepository.findById(login.getUsername());//client
		if (existing.isEmpty()) {
			if(Strings.isBlank(login.getPassword())){
				return new ResponseEntity("Password can not be blank", HttpStatus.BAD_REQUEST);
			}
			login.setAccountStatus("Active");
			login.setIsAdmin("N");
			try {
				login.setPassword(encryptionUtil.encrypt(login.getPassword()));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			loginRepository.save(login);
			return new ResponseEntity("User created successfully", HttpStatus.CREATED);
		}
		return new ResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Login> checkUserLogin(Login login) {
		Optional<Login> existing = loginRepository.findById(login.getUsername());
		if (existing.isPresent()) {
			String decrypted_password = "";
			try {
				decrypted_password = encryptionUtil.decrypt(existing.get().getPassword());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if (login.getPassword().equals(decrypted_password)){
				return new ResponseEntity(existing, HttpStatus.OK);
			}
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> passwordReset(PasswordDetails passwordDetails) {
		Optional<User> existing1 = userRepository.findByUsername(passwordDetails.getUsername());
		if (existing1.isPresent()) {
			if (passwordDetails.getMobileNo().equals(existing1.get().getMobileNumber())) {
				Optional<Login> existing = loginRepository.findById(passwordDetails.getUsername());
				if (existing.isPresent()) {
					if (Strings.isBlank(passwordDetails.getPassword())) {
						return new ResponseEntity("Password can not be blank", HttpStatus.BAD_REQUEST);
					}
					Login loginDetails = existing.get();
					try {
						loginDetails.setPassword(encryptionUtil.encrypt(passwordDetails.getPassword()));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					loginRepository.save(loginDetails);
					return new ResponseEntity<>("Password Updated Successfully", HttpStatus.OK);
				}

			}
			return new ResponseEntity<>("Mobile number does not match", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>("UserName Invalid", HttpStatus.BAD_REQUEST);
	}
}
