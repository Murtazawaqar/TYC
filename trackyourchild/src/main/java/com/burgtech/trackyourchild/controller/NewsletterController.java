package com.burgtech.trackyourchild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Newsletter;
import com.burgtech.trackyourchild.repository.NewsletterRepository;

@RestController
public class NewsletterController 
{
	@Autowired
	NewsletterRepository newsletterRepository;
	
	public List<Newsletter> fetchAllNewsletters()
	{
		return newsletterRepository.findAll();
	}
	
	public List<Newsletter> findNewsletterByStatus(Integer status)
	{
		return newsletterRepository.findByStatus(status);
	}
	
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
