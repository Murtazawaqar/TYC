package com.burgtech.trackyourchild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.burgtech.trackyourchild.model.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long>
{
	public Newsletter findByIdAndName(Long id, String name);
	
	public List<Newsletter> findByStatus(Integer status);
}
