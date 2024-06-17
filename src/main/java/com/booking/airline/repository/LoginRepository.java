package com.booking.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.airline.model.LoginDetails;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails, String>{

}
