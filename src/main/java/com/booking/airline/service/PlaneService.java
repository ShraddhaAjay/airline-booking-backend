package com.booking.airline.service;

import com.booking.airline.model.Login;
import com.booking.airline.model.Plane;
import com.booking.airline.model.User;
import com.booking.airline.repository.PlaneRepository;
import com.booking.airline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
@Service
public class PlaneService {
    @Autowired
    private PlaneRepository planeRepository;

    public ResponseEntity<String> addPlaneDetails(Plane plane) {
        Optional<Plane> existingPlane = planeRepository.findById(plane.getId());
        if (existingPlane.isEmpty()) {        //Add Id
            planeRepository.save(plane);
            return new ResponseEntity<>("plane id added successfully", HttpStatus.OK);
        }
        //plane detail are existing
        else {
            return new ResponseEntity<String>("plane id is already existing", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Plane> getPlaneInfo(int id) {
        Optional<Plane> existingPlane = planeRepository.findById(id);
        if (existingPlane.isPresent()) {
            return new ResponseEntity<>(existingPlane.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Plane>> getallplane() {
        List<Plane> planeList = planeRepository.findAll();
        return new ResponseEntity<>(planeList, HttpStatus.OK);

    }

    public ResponseEntity<String> updatePlaneinfo(Plane plane) {

        Optional<Plane> planeInfo= planeRepository.findById(plane.getId());
        if (planeInfo.isPresent()) {
            Plane existingPlane = planeInfo.get();    //persisted user info object
            existingPlane.setName(plane.getName());
            existingPlane.setSpeed(plane.getSpeed());
            existingPlane.setEconomySeats(plane.getEconomySeats());
            existingPlane.setBusinessSeats(plane.getBusinessSeats());
            existingPlane.setTotalSeats(plane.getTotalSeats());
            existingPlane.setCurrentLocation(plane.getCurrentLocation());
            existingPlane.setLastLocation(plane.getLastLocation());
            existingPlane.setStatus(plane.getStatus());
            planeRepository.save(existingPlane);
            return new ResponseEntity<>("Plane details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Id invalid", HttpStatus.BAD_REQUEST);



    }
}
