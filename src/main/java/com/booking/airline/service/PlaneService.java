package com.booking.airline.service;

import com.booking.airline.model.Plane;
import com.booking.airline.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    @Autowired
    private PlaneRepository planeRepository;

    public ResponseEntity<String> addPlaneDetails(Plane plane) {
        if (plane!=null) {        //Add Id
            planeRepository.save(plane);
            return new ResponseEntity<>("plane added successfully", HttpStatus.OK);
        }
        else { ////plane detail are existing
            return new ResponseEntity<String>("details not valid", HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<Plane>getPlaneById(Integer id){
       Optional <Plane> planeid= planeRepository.findById(id);
       if(planeid.isPresent()){
         return new ResponseEntity<>(planeid.get(),HttpStatus.OK)  ;
       }
       return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updatePlaneinfo(Plane plane) {

        Optional<Plane> planeInfo= planeRepository.findById(plane.getId());
        if (planeInfo.isPresent()) {
            Plane existingPlane = planeInfo.get();    //persisted user info object
            existingPlane.setName(plane.getName());
            existingPlane.setSeatCapacity(plane.getSeatCapacity());
            planeRepository.save(existingPlane);
            return new ResponseEntity<>("Plane details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Id invalid", HttpStatus.BAD_REQUEST);
    }

}
