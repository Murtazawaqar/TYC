package com.burgtech.trackyourchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgtech.trackyourchild.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>
{
	public UserType findByCode(String code);
}
