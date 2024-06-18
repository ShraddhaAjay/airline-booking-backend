package com.booking.airline.controller;

import com.booking.airline.model.Plane;
import com.booking.airline.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaneController {
    @Autowired
    private PlaneService planeService;

    @PostMapping("/plane-info")
    public ResponseEntity<String> addPlaneDetail(@RequestBody Plane plane) {
        return planeService.addPlaneDetails(plane);
    }

    @GetMapping("/get-planeinfo")
    public ResponseEntity<Plane> getPlaneInfo(@RequestParam("id") int id) {    //get by id
        return planeService.getPlaneInfo(id);
    }

    @GetMapping("/getall-plane")
    public ResponseEntity<List<Plane>> getallplane() {      //getByAll
        return planeService.getallplane();
    }

    @PutMapping("/update-planeinfo")
    public ResponseEntity<String> updatePlaneinfo(@RequestBody Plane plane) {
        return planeService.updatePlaneinfo(plane);
    }
}
