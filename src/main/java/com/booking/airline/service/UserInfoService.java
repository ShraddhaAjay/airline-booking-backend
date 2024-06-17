package com.booking.airline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.booking.airline.model.UserInfo;
import com.booking.airline.repository.UserInfoRepository;

public class UserInfoService {

	@Autowired
	private UserInfoRepository UserInfoRepository;
	public List<UserInfo> getAllUsers() {
		return UserInfoRepository.findAll();
	}

	

}
