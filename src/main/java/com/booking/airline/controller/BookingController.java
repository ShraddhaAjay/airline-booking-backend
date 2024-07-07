package com.booking.airline.controller;

import com.booking.airline.model.Booking;
import com.booking.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(originPatterns = "*")
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

    @DeleteMapping("/booking")
    public ResponseEntity<String> updateBooking(@RequestParam("bid") String bid) {
        return bookingService.deleteBooking(bid);

    }
    @GetMapping("/getUserBookings")
    public ResponseEntity<List<Booking>> getUserBookings(@RequestParam("username") String username){
        return bookingService.getUserBookings(username);
    }
}
