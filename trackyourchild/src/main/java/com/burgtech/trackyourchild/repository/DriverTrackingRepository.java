package com.burgtech.trackyourchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.DriverTracking;

@Repository
public interface DriverTrackingRepository extends JpaRepository<DriverTracking, Long> 
{
	
}
