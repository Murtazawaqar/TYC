package com.burgtech.trackyourchild.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.BranchDriver;
import com.burgtech.trackyourchild.model.Child;
import com.burgtech.trackyourchild.model.DriverTracking;
import com.burgtech.trackyourchild.model.School;
import com.burgtech.trackyourchild.model.User;
import com.burgtech.trackyourchild.model.UserType;
import com.burgtech.trackyourchild.pojos.AddBranchPojo;
import com.burgtech.trackyourchild.pojos.AddChildPojo;
import com.burgtech.trackyourchild.pojos.BranchDriverPojo;
import com.burgtech.trackyourchild.pojos.DriverTrackingPojo;
import com.burgtech.trackyourchild.pojos.ResponsePojo;
import com.burgtech.trackyourchild.pojos.SigninPojo;
import com.burgtech.trackyourchild.pojos.SignupPojo;
import com.burgtech.trackyourchild.repository.UserRepository;

@RestController
@RequestMapping("/tyc")
public class RequestController 
{
	@Autowired
	UserController userController;
	@Autowired
	UserTypeController userTypeController;
	@Autowired
	BranchController branchController;
	@Autowired
	ChildController childController;
	@Autowired
	private SchoolController schoolController;
	@Autowired
	private BranchDriverController branchDriverController;
	@Autowired
	private DriverTrackingController driverTrackingController;
	
	@PostMapping("/signin")
	public ResponsePojo signIn(@Valid @RequestBody SigninPojo signin)
	{
		User user = userController.findUserByEmailAndPassword(signin.getEmail(), signin.getPassword());
		
		if(user != null)
		{
			return new ResponsePojo("SUCC_AUTH_01","Authentication Successful","");
		}
		
		return new ResponsePojo("ERR_AUTH_01","Authentication Failed","Incorrect Email / Password");
	}
	
	@PostMapping("/signup")
	public ResponsePojo signUp(@Valid @RequestBody SignupPojo signup)
	{
		User user = new User();
		BeanUtils.copyProperties(signup, user);
		
		UserType userType = userTypeController.findUserTypeByCode(signup.getUserType());	
		user.setUserType(userType);
		
		user = userController.createNewUser(user);
		
		if(user != null)
		{
			return new ResponsePojo("SUCC_SIGN_01","Signup Successful","");
		}
		
		return new ResponsePojo("ERR_SIGN_01","Signup Failed","");
	}
	
	@PostMapping("/addChild")
	public ResponsePojo addNewChild(@Valid @RequestBody AddChildPojo addChild)
	{
		Child child = new Child();
		BeanUtils.copyProperties(addChild, child);
		
		User parent = userController.findUserByEmail(addChild.getParentEmail());
		Branch branch = branchController.findBranchByName(addChild.getBranchName());
		
		if(parent != null && branch != null)
		{	
			child.setParent(parent);
			child.setBranch(branch);
			
			child = childController.createNewChild(child);
			
			if(child != null)
			{
				return new ResponsePojo("SUCC_ADDC_01","Child added successfully.","");
			}
			
			return new ResponsePojo("ERR_SRV_00","Internal Server Error","");
		}
		
		return new ResponsePojo("ERR_IVLD_01","Invalid Parent / Branch.","");
	}
	
	@PostMapping("/addSchool")
	public ResponsePojo addSchool(@Valid @RequestBody School school)
	{
		school = schoolController.addNewSchool(school);
		
		if(school != null)
		{
			return new ResponsePojo("SUCC_ADDS_01","New School added successfully","");
		}
		
		return new ResponsePojo("ERR_DUPL_01","School already exist (Duplicate School Name)","");
	}
	
	@PostMapping("/addBranch")
	public ResponsePojo addBranch(@Valid @RequestBody AddBranchPojo addBranchPojo)
	{
		School school = schoolController.findSchoolByName(addBranchPojo.getSchoolName());
		
		if(school != null)
		{
			Branch branch = new Branch();
			BeanUtils.copyProperties(addBranchPojo, branch);
			
			branch.setSchool(school);
			
			branch = branchController.addNewBranch(branch);
			
			if(branch != null)
			{
				return new ResponsePojo("SUCC_ADDB_01","New Branch added successfully","");
			}
			
			return new ResponsePojo("ERR_DUPL_02","Branch already exist (Duplicate Branch Name)","");
		}
		
		return new ResponsePojo("ERR_IVLD_02","Invalid School Name","");
	}
	
	@PostMapping("/assignBranchDriver")
	public ResponsePojo assignDriverToBranch(@Valid @RequestBody BranchDriverPojo branchDriverPojo)
	{
		BranchDriver branchDriver = new BranchDriver();
		BeanUtils.copyProperties(branchDriverPojo,branchDriver);
		
		User driver = userController.findUserByEmail(branchDriverPojo.getDriver());
		Branch branch = branchController.findBranchByName(branchDriverPojo.getBranch());
		
		if(driver != null && branch != null)
		{	
			branchDriver.setBranch(branch);
			branchDriver.setDriver(driver);
			
			branchDriver = branchDriverController.saveBranchDriver(branchDriver);
			
			if(branchDriver != null)
			{
				return new ResponsePojo("SUCC_ADDBD_01","Driver assigned successfully","");
			}
			
			return new ResponsePojo("ERR_DUPL_03","Driver already assigned to that Branch","");
		}
		
		return new ResponsePojo("ERR_IVLD_01","Invalid Driver / Branch.","");
	}
	
	@PostMapping("/addDriverCoordinates")
	public ResponsePojo insertDriverCoordinates(@Valid @RequestBody DriverTrackingPojo driverTrackingPojo)
	{
		DriverTracking driverCoordinates = new DriverTracking();
		BeanUtils.copyProperties(driverTrackingPojo,driverCoordinates);
		
		User driver = userController.findUserByEmail(driverTrackingPojo.getDriver());
		
		if(driver != null)
		{
			driverCoordinates.setDriver(driver);
			driverTrackingController.saveCoordinated(driverCoordinates);
			
			return new ResponsePojo("SUCC_PDC_01","Coordinates Persisted.","");
		}
		
		return new ResponsePojo("ERR_IVLD_01","Invalid Driver","");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
