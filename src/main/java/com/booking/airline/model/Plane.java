package com.booking.airline.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;				// Plane ID
    private String name;			// name
    private Double speed;   		// speed in kmph
    private Integer economySeats;  	// total economy seats
    private Integer businessSeats;	// total business seats
    private Integer totalSeats;		// total seats
    private String currentLocation; // headed towards
    private String lastLocation;    // started from
    private String status; 			// plane status : AVA/UNA/FLY
}
