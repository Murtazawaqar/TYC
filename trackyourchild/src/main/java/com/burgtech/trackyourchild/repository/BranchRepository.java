package com.burgtech.trackyourchild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.School;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>
{	
	public Branch findByName(String name);
	
	public List<Branch> findBySchool(School school);
}
