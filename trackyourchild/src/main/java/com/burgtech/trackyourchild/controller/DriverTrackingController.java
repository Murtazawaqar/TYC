package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.DriverTracking;
import com.burgtech.trackyourchild.repository.DriverTrackingRepository;

@RestController
public class DriverTrackingController 
{
	@Autowired
	private DriverTrackingRepository driverTrackingRepository;
	
	public void saveCoordinated(DriverTracking driverCoordinates)
	{
		driverTrackingRepository.save(driverCoordinates);
	}
}
