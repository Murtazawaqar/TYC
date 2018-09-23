package com.burgtech.trackyourchild.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.burgtech.trackyourchild.exception.ResourceNotFoundException;
import com.burgtech.trackyourchild.model.User;
import com.burgtech.trackyourchild.model.UserType;
import com.burgtech.trackyourchild.pojos.SignupPojo;
import com.burgtech.trackyourchild.repository.UserRepository;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tyc")
public class UserController 
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserTypeController userTypeController;
	
	//Test method
	@GetMapping("/test")
	public String testing()
	{
		return "Hello World!";
	}
	
	//Authenticating user
	public User findUserByEmailAndPassword(String email, String password)
	{
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public User createNewUser(User user)
	{
		return userRepository.save(user);
	}
	
	
	//Get all users
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
//	//Add new user
//	@PostMapping("/users")
//	public User addNewUser(@Valid @RequestBody SignupPojo signupUser)
//	{
//		User user = new User();
//		BeanUtils.copyProperties(signupUser, user);
//		
//		UserType userType = userTypeController.findUserTypeByCode(signupUser.getUserType());	
//		user.setUserType(userType);
//
//		return userRepository.save(user);
//	}
	
	
	//Find a user by Id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId)
	{
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}
	
	//Update a user
}
