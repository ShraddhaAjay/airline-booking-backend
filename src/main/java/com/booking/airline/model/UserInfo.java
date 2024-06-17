package com.booking.airline.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
@Entity
@Data

public class UserInfo {
	
	@Id
	private String userID;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private long mobileNumber;
	private String gender;
	private int age;
	private String username;

}
