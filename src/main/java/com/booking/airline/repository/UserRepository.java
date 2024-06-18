package com.booking.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.airline.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{

    Optional<User> findByUsername(String username);
}
