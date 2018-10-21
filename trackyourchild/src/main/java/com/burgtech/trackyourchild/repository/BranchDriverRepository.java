package com.burgtech.trackyourchild.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.burgtech.trackyourchild.model.BranchDriver;

@Repository
public interface BranchDriverRepository extends JpaRepository<BranchDriver, Long>
{

}
