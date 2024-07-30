package com.booking.airline.service;

import com.booking.airline.model.Plane;
import com.booking.airline.model.Route;
import com.booking.airline.model.Schedule;
import com.booking.airline.repository.PlaneRepository;
import com.booking.airline.repository.RouteRepository;
import com.booking.airline.repository.ScheduleRepository;
import com.booking.airline.util.FairCalculator;
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
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PlaneRepository planeRepository;

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

    public ResponseEntity<String> addSchedule(Schedule schedule) {
        //Validating source and destination from route
        Optional<Route> existing = routeRepository.findBySourceAndDestination(schedule.getSource(), schedule.getDestination());
        if (existing.isEmpty()) {
            return new ResponseEntity<>("Route is invalid", HttpStatus.BAD_REQUEST);
        }
        //validating plane ID from
        Optional<Plane> existingPlane = planeRepository.findById(schedule.getPlaneId());
        if (existingPlane.isEmpty()) {
            return new ResponseEntity<>("Invalid plane details", HttpStatus.BAD_REQUEST);
        }
        //validating current time with departure time of schedule
        LocalDateTime today = LocalDateTime.now();
        if (schedule.getDepartureDateTime().isBefore(today)) {
            return new ResponseEntity<>("Invalid date time", HttpStatus.BAD_REQUEST);
        }
//        Optional<Schedule> existingSchedule= scheduleRepository.findByIdSourceAndDestination();
        double fare = FairCalculator.calculateSimpleFare(existingPlane.get().getSpeed(), existing.get().getDistance());
        schedule.setFare(fare);
        scheduleRepository.save(schedule);
        return new ResponseEntity<>("Schedule added successfully",HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteSchedule(String id) {
        if(scheduleRepository.findById(id).isPresent()){
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>("Schedule Deleted Successfully..!", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<String> updateSchedule(Schedule schedule) {
        Optional<Route> existing = routeRepository.findBySourceAndDestination(schedule.getSource(), schedule.getDestination());
        if (existing.isEmpty()) {
            return new ResponseEntity<>("Route is invalid", HttpStatus.BAD_REQUEST);
        }
        Optional<Plane> existingPlane = planeRepository.findById(schedule.getPlaneId());
        if (existingPlane.isEmpty()) {
            return new ResponseEntity<>("Invalid plane details", HttpStatus.BAD_REQUEST);
        }
        LocalDateTime today = LocalDateTime.now();
        if (schedule.getDepartureDateTime().isBefore(today)) {
            return new ResponseEntity<>("Invalid date time", HttpStatus.BAD_REQUEST);
        }
        Optional<Schedule> existingSch = scheduleRepository.findById(schedule.getId());
        if (existingSch.isPresent()) {
            Schedule existingSchedule = existingSch.get();

            existingSchedule.setId(schedule.getId());
            existingSchedule.setSource(schedule.getSource());
            existingSchedule.setDestination(schedule.getDestination());
            existingSchedule.setArrivalDateTime(schedule.getArrivalDateTime());
            existingSchedule.setDepartureDateTime(schedule.getDepartureDateTime());
            existingSchedule.setPlaneId(schedule.getPlaneId());

            double fare = FairCalculator.calculateSimpleFare(existingPlane.get().getSpeed(), existing.get().getDistance());
            schedule.setFare(fare);
            scheduleRepository.save(existingSchedule);
            return new ResponseEntity<>("Schedule Updated Successfully..!", HttpStatus.OK);

        }

        return new ResponseEntity<>("ScheduleId Invalid",HttpStatus.CREATED);
    }
}
