package com.booking.airline.service;

import com.booking.airline.model.Booking;
import com.booking.airline.repository.BookingRepository;
import com.booking.airline.util.SeatNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<String> addBooking(Booking booking) {
        booking.setBookingDateTime(LocalDateTime.now());
        String seatNumber = SeatNumberGenerator.generateSeatNumber(booking.getJourneyDateTime(), booking.getPlaneId());
        if(seatNumber==null)
            new ResponseEntity<>("Booking full", HttpStatus.FAILED_DEPENDENCY);
        booking.setSeatNumber(seatNumber.substring(booking.getPlaneId().length()+6));
        booking = bookingRepository.save(booking);
        return new ResponseEntity<>("Booking generated, Booking Id:" + booking.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings=bookingRepository.findAll();
        return new ResponseEntity<>(bookings,HttpStatus.OK) ;
    }

    public ResponseEntity<String> updateBooking(Booking booking) {
        Optional<Booking> existing = bookingRepository.findById(booking.getId());
        if (existing.isPresent()) {
            Booking existingBooking = existing.get();
            existingBooking.setSource(booking.getSource());
            existingBooking.setDestination(booking.getDestination());
            existingBooking.setFare(booking.getFare());
            existingBooking.setBookedBy(existingBooking.getBookedBy());
            existingBooking.setBookingDateTime(LocalDateTime.now());
            existingBooking.setPlaneId(booking.getPlaneId());
            existingBooking.setSeatNumber(booking.getSeatNumber());
            bookingRepository.save(existingBooking);
            return new ResponseEntity<>("Booking details updated", HttpStatus.OK);
        }
        return new ResponseEntity("Invalid Booking ID", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Booking>> getUserBookings(String username) {
        return new ResponseEntity<>(bookingRepository.findByUsername(username),HttpStatus.OK);
    }
}

