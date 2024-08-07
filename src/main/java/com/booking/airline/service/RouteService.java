package com.booking.airline.service;

import com.booking.airline.model.Route;
import com.booking.airline.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private static List<String> Cities = new ArrayList();
    static {
        Cities.add("Select City"); Cities.add("Pune");Cities.add("Solapur");Cities.add("Delhi");Cities.add("Mumbai");Cities.add("Goa");Cities.add("Banglore");
        Cities.add("Satara");Cities.add("Sangali");Cities.add("Latur");Cities.add("Ahemdabad");
    }

    @Autowired
    private RouteRepository routeRepository;

    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routeList = routeRepository.findAll();
        return new ResponseEntity<>(routeList, HttpStatus.OK);
    }


    public ResponseEntity<String> addRouteDetails(Route route) {
        Optional<Route> existing = routeRepository.findBySourceAndDestination(route.getSource(),route.getDestination());
        if (existing.isEmpty()) {
            routeRepository.save(route);
            return new ResponseEntity<>("Route created successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Existing route", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Route> getRouteById(int id) {
        Optional<Route> existing = routeRepository.findById(id);
        if (existing.isPresent()) {
            return new ResponseEntity<>(existing.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> updateRoute(Route route) {

        Optional<Route> existing = routeRepository.findById(route.getId());
        if (existing.isPresent()) {
            Route existingRoute = existing.get();

            existingRoute.setId(route.getId());
            existingRoute.setSource(route.getSource());
            existingRoute.setDestination(route.getDestination());
            existingRoute.setDistance(route.getDistance());

            routeRepository.save(existingRoute);
            return new ResponseEntity<>("Route Updated Successfully..!", HttpStatus.OK);

        }
        return new ResponseEntity<>("Invalid Route Id", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteRoute(int id) {
        if(routeRepository.findById(id).isPresent()){
            routeRepository.deleteById(id);
            return new ResponseEntity<>("Route Deleted Successfully..!", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getAllCities() {
        return new ResponseEntity<>(Cities, HttpStatus.OK);
    }
}
