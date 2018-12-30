package com.burgtech.trackyourchild.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.User;
import com.burgtech.trackyourchild.model.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
	public List<User> findByUserType(UserType userType);
}
