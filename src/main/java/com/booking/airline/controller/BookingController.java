package com.booking.airline.controller;

import com.booking.airline.model.Booking;
import com.booking.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @GetMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/booking")
    public ResponseEntity<String> updateBooking(@RequestBody Booking booking) {
        return bookingService.updateBooking(booking);

    }
    @GetMapping("/getByID")
    public ResponseEntity<Booking> getByBookingId(@RequestParam("id") String ID){
        return bookingService.getByBookingId(ID);
    }
}
