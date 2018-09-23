package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.UserType;
import com.burgtech.trackyourchild.repository.UserTypeRepository;

@RestController
public class UserTypeController 
{
	@Autowired
	UserTypeRepository userTypeRepository = null;
	
	public UserType findUserTypeByCode(String code)
	{
		return userTypeRepository.findByCode(code);
	}
}
