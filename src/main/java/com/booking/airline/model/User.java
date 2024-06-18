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
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
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
