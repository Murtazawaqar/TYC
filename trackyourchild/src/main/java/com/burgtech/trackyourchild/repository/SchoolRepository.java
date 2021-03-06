package com.burgtech.trackyourchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>
{
	public School findByName(String name);
}
