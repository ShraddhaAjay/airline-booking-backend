package com.booking.airline.service;

import com.booking.airline.model.Booking;
import com.booking.airline.repository.BookingRepository;
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
            existingBooking.setNoOfSeats(booking.getNoOfSeats());
            existingBooking.setTotalFare(booking.getTotalFare());
            existingBooking.setBookedBy(existingBooking.getBookedBy());
            existingBooking.setBookingDateTime(LocalDateTime.now());
            existingBooking.setPlaneId(booking.getPlaneId());
            existingBooking.setSeatNumbers(booking.getSeatNumbers());
            bookingRepository.save(existingBooking);
            return new ResponseEntity<>("Booking details updated", HttpStatus.OK);
        }
        return new ResponseEntity("Invalid Booking ID", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Booking> getByBookingId(String ID) {
        Optional<Booking> existing = bookingRepository.findById(ID);
        if (existing.isPresent()) {
            return new ResponseEntity<>(existing.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }
}

