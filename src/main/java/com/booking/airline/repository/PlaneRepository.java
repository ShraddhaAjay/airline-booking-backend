package com.booking.airline.repository;

import com.booking.airline.model.Plane;
import com.booking.airline.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository <Plane, Id> {


    Optional<Plane> findById(Integer planeId);
}
