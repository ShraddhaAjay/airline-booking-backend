package com.booking.airline.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue(generator = "bid_gen")
	@GenericGenerator(name = "bid_gen", strategy = "com.booking.airline.util.BookingIdGenerator")
	private String id;                      // auto-generated booking id
	private String source;                  // source city
	private String destination;             // destination city
	private LocalDateTime journeyDateTime;  // journey date and time
	private Double fare;                    // fare per seat
	@GeneratedValue(generator = "sno_gen")
	@GenericGenerator(name = "sno_gen", strategy = "com.booking.airline.util.SeatNoGenerator")
	private String seatNumber;              // allocated seat numbers
	private LocalDateTime bookingDateTime;  // time of booking
	private String bookedBy; 			    //user who booked
	private String planeId;   		     	// plane which is available for the journey
}
