package com.burgtech.trackyourchild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.Child;
import com.burgtech.trackyourchild.model.User;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long>
{
	public Child findByIdAndParent(Long id, User parent);
	
	public List<Child> findByParent(User parent);
}
