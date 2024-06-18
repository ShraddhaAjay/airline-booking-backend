package com.booking.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booking.airline.service.UserService;
import com.booking.airline.model.User;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/users")
    public ResponseEntity<String> addUserDetails(@RequestBody User user) {
        return userService.addUserDetails(user);
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUserDetails(@RequestBody User user) {
        return userService.updateUserDetails(user);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getByUserID") // if the userID is not existing then throw a message "Invalid UserID".
    public ResponseEntity<User> getUserById(@RequestParam("id") String userID) {
        return userService.getUserById(userID);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUserByUerName(@RequestParam("UserID") String userID) {
        return userService.deleteUserByUerName(userID);
    }
}




