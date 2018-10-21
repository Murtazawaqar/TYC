package com.burgtech.trackyourchild.pojos;

import org.springframework.stereotype.Component;

import com.burgtech.trackyourchild.model.User;

@Component
public class DriverTrackingPojo 
{
	private Long id;
	private String driver;
	private double latitude;
	private double longitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
