package com.booking.airline.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Data
public class Schedule {
	@Id
	@GeneratedValue(generator = "sid_gen")
	@GenericGenerator(name = "sid_gen", strategy = "com.booking.airline.util.ScheduleIdGenerator")
	private String id;                      // auto-generated booking id
	private String source;                  // source city
	private String destination;             // destination city
	private LocalDateTime departureDateTime;// journey start date and time
	private LocalDateTime arrivalDateTime;  // journey end date and time
	private Double fare;                    // fare per seat // calculate using plane speed and time of travel criteria
	private Integer planeId;   		     	// plane which is available for the journey
}

