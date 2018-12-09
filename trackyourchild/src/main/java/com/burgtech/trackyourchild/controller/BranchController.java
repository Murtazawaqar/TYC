package com.burgtech.trackyourchild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.School;
import com.burgtech.trackyourchild.repository.BranchRepository;

@RestController
public class BranchController 
{
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch findBranchByName(String name)
	{
		return branchRepository.findByName(name);
	}
	
	public Branch findBranchById(Long id)
	{
		return branchRepository.findById(id).orElse(null);
	}
	
	public List<Branch> findBranchBySchool(School school)
	{
		return branchRepository.findBySchool(school);
	}
	
	public List<Branch> fetchAllBranches()
	{
		return branchRepository.findAll();
	}
	
	public Branch addNewBranch(Branch branch)
	{
		return branchRepository.save(branch);
	}
	
	public void deleteBranch(Branch branch)
	{
		branchRepository.delete(branch);
	}
}
