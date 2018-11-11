package com.burgtech.trackyourchild.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.BranchDriver;
import com.burgtech.trackyourchild.model.User;

@Repository
public interface BranchDriverRepository extends JpaRepository<BranchDriver, Long>
{
	public BranchDriver findByBranchAndDriver(Branch branch, User Driver);
}
