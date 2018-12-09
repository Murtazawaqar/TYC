package com.burgtech.trackyourchild.controller;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.burgtech.trackyourchild.model.Branch;
import com.burgtech.trackyourchild.model.BranchDriver;
import com.burgtech.trackyourchild.model.Child;
import com.burgtech.trackyourchild.model.DriverTracking;
import com.burgtech.trackyourchild.model.Newsletter;
import com.burgtech.trackyourchild.model.School;
import com.burgtech.trackyourchild.model.User;
import com.burgtech.trackyourchild.model.UserType;
import com.burgtech.trackyourchild.pojos.AddBranchPojo;
import com.burgtech.trackyourchild.pojos.AddChildPojo;
import com.burgtech.trackyourchild.pojos.AddNewsletterPojo;
import com.burgtech.trackyourchild.pojos.BranchDriverPojo;
import com.burgtech.trackyourchild.pojos.DeleteChildPojo;
import com.burgtech.trackyourchild.pojos.DeleteSchoolPojo;
import com.burgtech.trackyourchild.pojos.DeleteUserPojo;
import com.burgtech.trackyourchild.pojos.DriverTrackingPojo;
import com.burgtech.trackyourchild.pojos.ResponsePojo;
import com.burgtech.trackyourchild.pojos.SigninPojo;
import com.burgtech.trackyourchild.pojos.SignupPojo;
import com.burgtech.trackyourchild.repository.BranchRepository;
import com.burgtech.trackyourchild.repository.ChildRepository;
import com.burgtech.trackyourchild.repository.UserRepository;

@RestController
@RequestMapping("/tyc")
public class RequestController 
{
	@Autowired
	private UserController userController;
	@Autowired
	private UserTypeController userTypeController;
	@Autowired
	private BranchController branchController;
	@Autowired
	private ChildController childController;
	@Autowired
	private SchoolController schoolController;
	@Autowired
	private BranchDriverController branchDriverController;
	@Autowired
	private DriverTrackingController driverTrackingController;
	@Autowired
	private NewsletterController newsletterController;
	
	//Test method
	@GetMapping("/test")
	public String testing()
	{
		return "Hello World!";
	}
	
	@GetMapping("/user/{email}")
	@ResponseBody
	public User getUserDetails(@PathVariable String email)
	{
		return userController.findUserByEmail(email);
	}
	
	@GetMapping("/school/{name}")
	@ResponseBody
	public School getSchoolDetails(@PathVariable String name)
	{
		return schoolController.findSchoolByName(name);
	}
	
	@GetMapping("/school/fetchAll")
	@ResponseBody
	public List<School> getAllSchoolDetails()
	{
		return schoolController.fetchAllSchools();
	}
	
	@GetMapping("/branch/{id}")
	@ResponseBody
	public Branch getBranchDetails(@PathVariable Long id)
	{
		return branchController.findBranchById(id);
	}
	
	@GetMapping("/branch/schoolId/{schoolId}")
	@ResponseBody
	public List<Branch> getAllBranchDetails(@PathVariable Long schoolId)
	{
		School school = schoolController.findSchoolById(schoolId);
		
		if(school != null)
		{
			return branchController.findBranchBySchool(school);
		}

		return null;
	}
	
	@GetMapping("/branch/fetchAll")
	@ResponseBody
	public List<Branch> getAllBranchDetails()
	{
		return branchController.fetchAllBranches();
	}
	
	@GetMapping("newsletter/fetchAll")
	@ResponseBody
	public List<Newsletter> getAllNewsletters()
	{
		return newsletterController.fetchAllNewsletters();
	}
	
	@GetMapping("newsletter/{status}")
	@ResponseBody
	public List<Newsletter> getNewslettersByStatus(@PathVariable Integer status)
	{
		return newsletterController.findNewsletterByStatus(status);
	}
	
	@GetMapping("branchDriver/{branchId}")
	@ResponseBody
	public List<BranchDriver> findDriversByBranch(@PathVariable Long branchId)
	{
		Branch branch = branchController.findBranchById(branchId);
		
		return branch != null ? branchDriverController.findByBranch(branch):null; 
	}
	
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
	
	@PostMapping("/addNewsletter")
	public ResponsePojo addNewsletter(@Valid @RequestBody AddNewsletterPojo newsletterPojo)
	{
		Newsletter newsletter = new Newsletter();
//		BeanUtils.copyProperties(newsletterPojo, newsletter);
		
		newsletter.setName(newsletterPojo.getName());
		newsletter.setContent(newsletterPojo.getContent());
		newsletter.setStatus(newsletterPojo.getStatus());
		
		LocalDate startDate = LocalDate.parse(newsletterPojo.getStartDate());
		LocalDate endDate = LocalDate.parse(newsletterPojo.getEndDate());
			
		newsletter.setStartDate(startDate);
		newsletter.setEndDate(endDate);
			
		if(startDate != null && endDate != null)
		{
			newsletter = newsletterController.saveNewsletter(newsletter);
		
			if(newsletter != null)
			{
				return new ResponsePojo("SUCC_AN_01","Nesletter successfully added.","");
			}
			
			else
			{
				return new ResponsePojo("ERR_01","Unknown Error","");
			}
		}
		
		return new ResponsePojo("ERR_IVDF_01","Invalid Date Format Provided.","");
	}
	
	@DeleteMapping("/user")
	public ResponsePojo deleteUser(@Valid @RequestBody DeleteUserPojo user)
	{
		boolean check = userController.deleteUser(user.getEmail(),user.getCode());
		
		if(check)
		{
			return new ResponsePojo("SUCC_DU_01","User successfully deleted.","");
		}
		
		return new ResponsePojo("ERR_IVU","User not found.","");
	}
	
	@DeleteMapping("/school")
	public ResponsePojo deleteSchool(@Valid @RequestBody DeleteSchoolPojo schoolPojo)
	{
		School school = schoolController.findSchoolByName(schoolPojo.getName());
		
		if(school != null)
		{
			List<Branch> branches = branchController.findBranchBySchool(school); 
			
			for (Branch branch : branches) 
			{
				branchController.deleteBranch(branch);
			}
			
			return new ResponsePojo("SUCC_DS_01","School & Branches successfully deleted.","");
		}
		
		return new ResponsePojo("ERR_IVS","Invalid School Provided.","");
	}
	
	@DeleteMapping("/child")
	public ResponsePojo deleteChild(@Valid @RequestBody DeleteChildPojo childPojo)
	{
		User parent = userController.findUserByEmail(childPojo.getParentEmail());
		
		boolean check;
		
		if(parent != null)
		{
			check = childController.deleteChild(childPojo.getChildId(), parent);
			
			if(check)
			{
				return new ResponsePojo("SUCC_DC_01","Child successfully deleted.","");
			}
			
			return new ResponsePojo("ERR_IVC_01","Child not found.","");
		}
		
		return new ResponsePojo("ERR_IVU","Parent not found.","");
	}
	
	@DeleteMapping("/newsletter")
	public ResponsePojo deleteNewsletter(@Valid @RequestBody AddNewsletterPojo newsletter)
	{
		boolean check = newsletterController.deleteNewsletter(newsletter.getId(), newsletter.getName());
		
		if(check)
		{
			return new ResponsePojo("SUCC_DN_01","Newsletter successfully deleted.","");
		}
		
		return new ResponsePojo("ERR_IVN","Newsletter not found.","");
	}
	
	@DeleteMapping("/branchDriver")
	public ResponsePojo deleteBranchDriver(@Valid @RequestBody BranchDriverPojo branchDriver)
	{
		//Branch name and Driver Email is required
		
		Branch branch = branchController.findBranchByName(branchDriver.getBranch());
		User driver = userController.findUserByEmail(branchDriver.getDriver());
		
		boolean check;
		
		if(branch != null && driver != null)
		{
			check = branchDriverController.deleteBranchDriver(branch, driver);
			
			if(check)
			{
				return new ResponsePojo("SUCC_DBD_01","Driver assignemnt successfully deleted.","");
			}
			
			return new ResponsePojo("ERR_01","Unknown Error","");
		}
		
		return new ResponsePojo("ERR_IVBD","Branch/Driver not found.","");
	}
}
