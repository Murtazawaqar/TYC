package com.burgtech.trackyourchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
}
