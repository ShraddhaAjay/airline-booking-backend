package com.booking.airline.controller;

import com.booking.airline.model.Route;
import com.booking.airline.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/add-route")
    public ResponseEntity<String> addRouteDetails(@RequestBody Route route){
        return routeService.addRouteDetails(route);
    }

    @GetMapping("/get-allroute")
    public ResponseEntity<List<Route>> getAllRoutes(){
       return routeService.getAllRoutes();
    }

    @GetMapping("/getroute-byid")
    public ResponseEntity<Route>  getRouteById(@RequestParam("id") int id){
        return routeService.getRouteById(id);
    }

    @PutMapping("/update-route")
    public ResponseEntity<String> updateRoute(@RequestBody Route route){
        return routeService.updateRoute(route);
    }

}
