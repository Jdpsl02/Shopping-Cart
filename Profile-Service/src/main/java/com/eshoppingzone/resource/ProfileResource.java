package com.eshoppingzone.resource;

import java.util.List;    
import java.util.Map;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.eshoppingzone.model.UserProfile;
import com.eshoppingzone.service.ProfileService;
import com.eshoppingzone.service.ProfileServiceImpl;
import com.eshoppingzone.exception.ProfileAlreadyExistsException;
import com.eshoppingzone.exception.ProfileNotFoundException;
import com.eshoppingzone.mailservice.EmailService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProfileResource {

	@Autowired
	private ProfileService profileServiceImpl;
	
	@Autowired
	final RestTemplate restTemplate;
	
	@Autowired
	private EmailService em;
	
	
	
	Logger logger= LoggerFactory.getLogger(ProfileResource.class);
	
	
	
	

	public ProfileResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
		
	}
	
	
	//adding new user profile
	@PostMapping("/user")
	public ResponseEntity<UserProfile>  addNewCustomerProfile ( @RequestBody  UserProfile userProfile) throws ProfileAlreadyExistsException{
				
			logger.info("add new Customer");
				UserProfile savedUser=	   profileServiceImpl.addNewCustomerProfile(userProfile);
				String body = "Hello "+userProfile.getFullName()+" "+"Welome to EShoppingZone,  your profile is created with fullName "+userProfile.getFullName()+" Start shopping :)";

				em.sendSimpleMail(userProfile.getEmailId(), body, "Profile Created");
				return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
				
	}
	

	//get all profiles 
	@GetMapping("/users")
	public List<UserProfile> getAllProfiles() {
		
		return profileServiceImpl.getAllProfiles();

	}
	
	//get profile by user profileId
	@GetMapping("/user/{profileId}")
	public ResponseEntity<UserProfile> getByProfileId(@PathVariable int profileId) throws ProfileNotFoundException{
		return new ResponseEntity<>( profileServiceImpl.getByProfileId(profileId),HttpStatus.OK);
	}
	
	//get user profile by  user mobile Number
	@GetMapping("/users/{mobileNumber}")
	public UserProfile getByPhoneNumber(@PathVariable Long mobileNumber) {
		return profileServiceImpl.findByMobileNo(mobileNumber);
	}
	
	// update user profile using user fullName
	@PutMapping("/user/update/{fullName}")
	public ResponseEntity<UserProfile> updateProfile(@PathVariable String fullName, @RequestBody UserProfile userProfile) throws ProfileNotFoundException {

		UserProfile updatedProfile= profileServiceImpl.updateProfile(fullName,userProfile);
		return ResponseEntity.ok(updatedProfile);
	}
	
	//delete user profile by user profileId
	@DeleteMapping("/user/delete/{profileId}")
	public Map<String,Boolean>  deleteProfile(@PathVariable int profileId) throws ProfileNotFoundException{
		logger.warn("delete method hit");
		return profileServiceImpl.deleteProfile(profileId);
		
	}
	
	//get user by user fullName
	@GetMapping("/userByName/{fullName}")
	public UserProfile getByUserName(@PathVariable String fullName) {
		logger.info("get by  fullname hit");
		
		return profileServiceImpl.getByUserName(fullName);
	}
	
	//not using
	@PostMapping("/merchant")
	public void addNewMerchantProfile(@RequestBody UserProfile userProfile) {
		
		profileServiceImpl.addNewMerchantProfile(userProfile);
	}
	//not using
	@PostMapping("/delivery")
	public void addNewdeliveryProfile(@RequestBody UserProfile userProfile) {
		
		profileServiceImpl.addNewDeliveryProfile(userProfile);
	}
	
	
}
