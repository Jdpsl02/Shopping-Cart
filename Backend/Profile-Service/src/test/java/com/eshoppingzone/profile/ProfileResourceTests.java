package com.eshoppingzone.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eshoppingzone.profile.UserProfileService.pojo.UserProfile;
import com.eshoppingzone.profile.UserProfileService.resource.ProfileResource;
import com.eshoppingzone.profile.UserProfileService.service.ProfileServiceImpl;

@SpringBootTest(classes= {ProfileResourceTests.class})
public class ProfileResourceTests {
	
	@Mock
	ProfileServiceImpl profileService;
	
	@InjectMocks 
	ProfileResource profileResource;
	
	
	List<UserProfile> profiles;
	UserProfile profile;
	
	@Test
	@Order(1)
	public void test_getAllProfiles() {
		
		profiles= new ArrayList<UserProfile>();
		profiles.add((new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null)));
		profiles.add(new UserProfile(2, "anuj", "csp.png", "gmail232@gmail.com", (long) 638734554, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null));
		
		when(profileService.getAllProfiles()).thenReturn(profiles);
		
		List<UserProfile> result=profileResource.getAllProfiles();
		
		assertEquals(2,result.size() );
	}
	
	
	@Test
	@Order(2)
	public void test_getProfileById() {
		
		
		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
		
		int profileId=1;
	
		
		when(profileService.getByProfileId(profileId)).thenReturn(profile);
		
		
		ResponseEntity<UserProfile> result=profileResource.getByProfileId(profileId);
		
		assertEquals(HttpStatus.OK,result.getStatusCode() );
		assertEquals(profileId, result.getBody().getProfileId());
	}
	
	@Test
	@Order(3)
	public void test_addProfile() {
			
		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
		
		when(profileService.addNewCustomerProfile(profile)).thenReturn(profile);
		ResponseEntity<UserProfile> result= profileResource.addNewCustomerProfile(profile);
		
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(profile, result.getBody());
	}
	
	
	@Test
	@Order(4)
	public void test_updateProfile() {
			
		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
		
		String fullName="shivam";
	
		
		when(profileService.getByUserName(fullName)).thenReturn(profile);
		when(profileService.updateProfile(fullName,profile)).thenReturn(profile);
		
		
		ResponseEntity<UserProfile> result= profileResource.updateProfile(fullName,profile);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("shivam", result.getBody().getFullName());
		assertEquals(1, result.getBody().getProfileId());
	}
	
	@Test
	@Order(5)
	public void test_deleteProfile() {
			
		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
		
		int profileId=1;
		
		Map<String,Boolean> response= new HashMap<String, Boolean>();
		response.put("Deleted", true);
		
		when(profileService.getByProfileId(profileId)).thenReturn(profile);
		when(profileService.deleteProfile(profileId)).thenReturn(response);
		Map<String,Boolean> result= profileResource.deleteProfile(profileId);
		
		
		
		assertEquals(response, result);
	}
	
}
