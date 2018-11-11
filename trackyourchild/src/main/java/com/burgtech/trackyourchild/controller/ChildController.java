package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.Child;
import com.burgtech.trackyourchild.model.User;
import com.burgtech.trackyourchild.repository.ChildRepository;

@RestController
public class ChildController 
{
	@Autowired
	private ChildRepository childRepository;
	
	public Child createNewChild(Child child)
	{
		return childRepository.save(child);
	}
	
	public Child findChildByParent(Long childId, User parent)
	{
		return childRepository.findByIdAndParent(childId,parent);
	}
	
	public boolean deleteChild(Long childId, User parent)
	{
		Child child = childRepository.findByIdAndParent(childId, parent);
		
		if(child != null)
		{
			childRepository.delete(child);
			return true;
		}
		
		return false;
	}
	
}
