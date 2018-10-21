package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.BranchDriver;
import com.burgtech.trackyourchild.repository.BranchDriverRepository;

@RestController
public class BranchDriverController 
{
	@Autowired
	private BranchDriverRepository branchDriverRepository;
	
	public BranchDriver saveBranchDriver(BranchDriver branchDriver)
	{
		return branchDriverRepository.save(branchDriver);
	}
}
