package com.booking.airline.controller;

import com.booking.airline.model.Schedule;
import com.booking.airline.service.ScheduleService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(originPatterns = "*")
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/getschedule-byid")
    public ResponseEntity<Schedule> getScheduleById(@RequestParam("id") String id){
        return  scheduleService.getScheduleById(id);
    }

    @GetMapping("/getall-schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/getschedules")
    public ResponseEntity<List<Schedule>> getSchedule(@RequestParam ("source") String source,
                                                      @RequestParam ("destination")String destination,
                                                      @RequestParam ("departureDateTime")String departureDateTime){
        return scheduleService.getSchedule(source,destination, LocalDateTime.parse(departureDateTime+"T00:00"));
    }
    @PostMapping("/add-schedule")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule){
        return  scheduleService.addSchedule(schedule);
    }

}
