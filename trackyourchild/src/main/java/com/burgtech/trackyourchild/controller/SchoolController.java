package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.School;
import com.burgtech.trackyourchild.repository.SchoolRepository;

@RestController
public class SchoolController 
{
	@Autowired
	private SchoolRepository schoolRepository;
	
	public School findSchoolByName(String name)
	{
		return schoolRepository.findByName(name);
	}
	
	public School addNewSchool(School school)
	{
		return schoolRepository.save(school);
	}
	
	public void deleteSchool(School school)
	{
		schoolRepository.delete(school);
	}
}
