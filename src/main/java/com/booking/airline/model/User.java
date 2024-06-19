package com.booking.airline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(generator = "uid_gen")
	@GenericGenerator(name = "uid_gen", strategy = "com.booking.airline.util.UserIdGenerator")
	private String userID;
	//TODO
	//UserID to be made in format as ARU001:-Airline User 001
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String gender;
	private Integer age;
	private String username;
}
