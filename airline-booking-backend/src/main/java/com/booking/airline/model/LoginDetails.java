package com.booking.airline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginDetails {

	@Id
	private String username;
	private String password;
	private String isAdmin;
	private String accountStatus;
}
