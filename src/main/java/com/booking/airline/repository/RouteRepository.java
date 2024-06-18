package com.booking.airline.repository;

import com.booking.airline.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Optional<Route> findBySourceAndDestination(String source,String destination);
}
