package com.burgtech.trackyourchild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Newsletter;
import com.burgtech.trackyourchild.repository.NewsletterRepository;

@RestController
public class NewsletterController 
{
	@Autowired
	NewsletterRepository newsletterRepository;
	
	public Newsletter saveNewsletter(Newsletter newsletter)
	{
		return newsletterRepository.save(newsletter);
	}
	
	public boolean deleteNewsletter(Long id, String name)
	{
		Newsletter newsletter = newsletterRepository.findByIdAndName(id, name);
		
		if(newsletter != null)
		{
			newsletterRepository.delete(newsletter);
			return true;
		}
		
		return false;
	}
}
