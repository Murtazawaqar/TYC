package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.BranchDriver;
import com.burgtech.trackyourchild.model.User;
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
	
	public boolean deleteBranchDriver(Branch branch, User driver)
	{
		BranchDriver branchDriver = branchDriverRepository.findByBranchAndDriver(branch, driver);
		
		if(branchDriver != null)
		{
			branchDriverRepository.delete(branchDriver);
			return true;
		}
		
		return false;
	}
}
