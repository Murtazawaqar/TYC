package com.burgtech.trackyourchild.pojos;

import org.springframework.stereotype.Component;

@Component
public class DeleteChildPojo 
{
	private String parentEmail;
	private Long childId;
	
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public Long getChildId() {
		return childId;
	}
	public void setChildId(Long childId) {
		this.childId = childId;
	}
}
