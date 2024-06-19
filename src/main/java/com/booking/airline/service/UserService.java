package com.booking.airline.service;

import java.util.List;
import java.util.Optional;

import com.booking.airline.model.Login;
import com.booking.airline.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.booking.airline.model.User;
import com.booking.airline.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public ResponseEntity<String> addUserDetails(User user) {
        Optional<Login> existing = loginRepository.findById(user.getUsername());
        if (existing.isPresent()) {
            Optional<User> existingUser = userRepository.findByUsername(user.getUsername());//old user coming for updation
            if (existingUser.isPresent()) {
                return new ResponseEntity<>("User profile already exists", HttpStatus.BAD_REQUEST);
            }
            userRepository.save(user);
            return new ResponseEntity<>("User data added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username invalid", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateUserDetails(User user) {
        Optional<User> existing = userRepository.findByUsername(user.getUsername());//old user coming for updation
        if (existing.isPresent()) {
            User existingUser = existing.get();    //persisted user info object
            existingUser.setAge(user.getAge());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setMobileNumber(user.getMobileNumber());
            existingUser.setGender(user.getGender());
            existingUser.setTitle(user.getTitle());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
            return new ResponseEntity<>("User data updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Username invalid", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(String userID) {
        Optional<User> user = userRepository.findById(userID);
        //TODO
        //throw exception when user not found
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteUserByUserID(String userID) {
        Optional<User> existingUser = userRepository.findById(userID);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>("User-" + userID + " does not exists", HttpStatus.BAD_REQUEST);
        }
        User user = existingUser.get();
        Optional<Login> existingLogin = loginRepository.findById(user.getUsername());
        existingLogin.get().setAccountStatus("Inactive");
        loginRepository.save(existingLogin.get());
        userRepository.delete(user);
        return new ResponseEntity<>("User-" + userID + " deleted successfully", HttpStatus.OK);
    }


}
