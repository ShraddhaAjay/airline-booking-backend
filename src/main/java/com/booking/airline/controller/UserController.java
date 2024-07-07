package com.booking.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booking.airline.service.UserService;
import com.booking.airline.model.User;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
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

    @GetMapping("/users") // if the userID is not existing then throw a message "Invalid UserID".
    public ResponseEntity<User> getUserById(@RequestParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUserByUserID(@RequestParam("UserID") String userID) {
        return userService.deleteUserByUserID(userID);
    }
}




