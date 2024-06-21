package com.booking.airline.repository;

import com.booking.airline.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {
 List<Schedule>  findBySourceAndDestinationAndDepartureDateTimeGreaterThan(String source , String destination, LocalDateTime departureDateTime);
}
