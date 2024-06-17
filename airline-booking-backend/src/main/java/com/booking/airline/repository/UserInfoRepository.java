package com.booking.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.airline.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

}
