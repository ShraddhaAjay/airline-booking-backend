package com.booking.airline.repository;

import com.booking.airline.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    @Query(value = "select * from booking where booked_by= :bookedBy", nativeQuery = true)
    List<Booking> findByUsername(@Param("bookedBy") String username);
}
