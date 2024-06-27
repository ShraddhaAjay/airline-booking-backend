package com.booking.airline.repository;

import com.booking.airline.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository <Plane, Integer> {


    Optional<Plane> findById(Integer planeId);
}
