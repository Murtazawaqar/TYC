package com.burgtech.trackyourchild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.School;
import com.burgtech.trackyourchild.repository.SchoolRepository;

@RestController
public class SchoolController 
{
	@Autowired
	private SchoolRepository schoolRepository;
	
	public School findSchoolById(Long id)
	{
		return schoolRepository.findById(id).orElse(null);
	}
	
	public School findSchoolByName(String name)
	{
		return schoolRepository.findByName(name);
	}
	
	public List<School> fetchAllSchools()
	{
		return schoolRepository.findAll();
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
