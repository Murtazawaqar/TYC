package com.burgtech.trackyourchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>
{
	public Branch findByName(String name);
}
