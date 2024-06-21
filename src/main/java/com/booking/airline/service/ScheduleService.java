package com.booking.airline.service;

import com.booking.airline.model.Schedule;
import com.booking.airline.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public ResponseEntity<Schedule> getScheduleById(String id) {
        Optional<Schedule>  existing=scheduleRepository.findById(id);
        if (existing.isPresent()){
          return new ResponseEntity<>(existing.get(), HttpStatus.OK) ;
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Schedule>> getAllSchedules() {
      List<Schedule> scheduleList=scheduleRepository.findAll();
      return new ResponseEntity<>(scheduleList,HttpStatus.OK);
    }

    public ResponseEntity<List<Schedule>> getSchedule(String source, String destination, LocalDateTime departureDateTime) {
       List<Schedule> existing=scheduleRepository.findBySourceAndDestinationAndDepartureDateTimeGreaterThan(source,destination,departureDateTime);
       return new ResponseEntity<>(existing,HttpStatus.OK);
    }
}
