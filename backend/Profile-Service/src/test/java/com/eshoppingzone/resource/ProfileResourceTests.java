package com.eshoppingzone.resource;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eshoppingzone.mailservice.EmailService;
import com.eshoppingzone.model.UserProfile;
import com.eshoppingzone.resource.ProfileResource;
import com.eshoppingzone.service.ProfileServiceImpl;
import com.eshoppingzone.service.ProfileService;

@SpringBootTest(classes= {ProfileResourceTests.class})
public class ProfileResourceTests {
	  @Mock
	    private ProfileService profileService;

	    @Mock
	    private RestTemplate restTemplate;

	    @Mock
	    private EmailService emailService;

	    @InjectMocks
	    private ProfileResource profileResource;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    
	    @Test
	    public void AddNewCustomerProfileTest() {
	        UserProfile userProfile = new UserProfile();
	        // Set up the userProfile object

	        when(profileService.addNewCustomerProfile(userProfile)).thenReturn(userProfile);

	        ResponseEntity<UserProfile> response = profileResource.addNewCustomerProfile(userProfile);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(userProfile, response.getBody());
	    }
	    
	    
	    

	    @Test
	    void getAllProfilesTest() {
	        // Arrange
	        List<UserProfile> profiles = new ArrayList<>();
	        profiles.add(new UserProfile());
	        when(profileService.getAllProfiles()).thenReturn(profiles);

	        // Act
	        List<UserProfile> result = profileResource.getAllProfiles();

	        // Assert
	        assertEquals(profiles, result);
	        verify(profileService, times(1)).getAllProfiles();
	    }

	    @Test
	    void getByProfileIdTest() {
	        // Arrange
	        int profileId = 1;
	        UserProfile userProfile = new UserProfile();
	        when(profileService.getByProfileId(profileId)).thenReturn(userProfile);
	        ResponseEntity<UserProfile> expectedResponse = new ResponseEntity<>(userProfile, HttpStatus.OK);

	        // Act
	        ResponseEntity<UserProfile> response = profileResource.getByProfileId(profileId);

	        // Assert
	        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
	        assertEquals(expectedResponse.getBody(), response.getBody());
	        verify(profileService, times(1)).getByProfileId(profileId);
	    }

	    @Test
	    void getByPhoneNumberTest() {
	        // Arrange
	        long mobileNumber = 1234567890L;
	        UserProfile userProfile = new UserProfile();
	        when(profileService.findByMobileNo(mobileNumber)).thenReturn(userProfile);

	        // Act
	        UserProfile result = profileResource.getByPhoneNumber(mobileNumber);

	        // Assert
	        assertEquals(userProfile, result);
	        verify(profileService, times(1)).findByMobileNo(mobileNumber);
	    }

	    @Test
	    void updateProfileTest() {
	        // Arrange
	        String fullName = "John Doe";
	        UserProfile userProfile = new UserProfile();
	        when(profileService.updateProfile(fullName, userProfile)).thenReturn(userProfile);
	        ResponseEntity<UserProfile> expectedResponse = new ResponseEntity<>(userProfile, HttpStatus.OK);

	        // Act
	        ResponseEntity<UserProfile> response = profileResource.updateProfile(fullName, userProfile);

	        // Assert
	        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
	        assertEquals(expectedResponse.getBody(), response.getBody());
	        verify(profileService, times(1)).updateProfile(fullName, userProfile);
	    }

	    @Test
	    public void testDeleteProfile() {
	        int profileId = 1;
	        Map<String, Boolean> expectedResponse = new HashMap<>();
	        expectedResponse.put("success", true);

	        when(profileService.deleteProfile(profileId)).thenReturn(expectedResponse);

	        Map<String, Boolean> response = profileResource.deleteProfile(profileId);

	        assertEquals(expectedResponse, response);
	    }
	
//	@Mock
//	ProfileServiceImpl profileService;
//	
//	@InjectMocks 
//	ProfileResource profileResource;
//	
//	
//	List<UserProfile> profiles;
//	UserProfile profile;
//	
//	  @Test
//	    @Order(1)
//	    public void test_getAllProfiles() {
//	        profiles = new ArrayList<>();
//	        profiles.add(new UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", 63873455L, "about",
//	                LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123", null));
//	        profiles.add(new UserProfile(2, "anuj", "csp.png", "gmail232@gmail.com", 638734554L, "about",
//	                LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123", null));
//
//	        when(profileService.getAllProfiles()).thenReturn(profiles);
//
//	        List<UserProfile> result = profileResource.getAllProfiles();
//
//	        assertEquals(2, result.size());
//	    }
//	
//	
//	@Test
//	@Order(2)
//	public void test_getProfileById() {
//		
//		
//		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
//				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
//		
//		int profileId=1;
//	
//		
//		when(profileService.getByProfileId(profileId)).thenReturn(profile);
//		
//		
//		ResponseEntity<UserProfile> result=profileResource.getByProfileId(profileId);
//		
//		assertEquals(HttpStatus.OK,result.getStatusCode() );
//		assertEquals(profileId, result.getBody().getProfileId());
//	}
//	
//	@Test
//	@Order(3)
//	public void test_addProfile() {
//			
//		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
//				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
//		
//		when(profileService.addNewCustomerProfile(profile)).thenReturn(profile);
//		ResponseEntity<UserProfile> result= profileResource.addNewCustomerProfile(profile);
//		
//		assertEquals(HttpStatus.CREATED, result.getStatusCode());
//		assertEquals(profile, result.getBody());
//	}
//	
//	
//	@Test
//	@Order(4)
//	public void test_updateProfile() {
//			
//		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
//				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
//		
//		String fullName="shivam";
//	
//		
//		when(profileService.getByUserName(fullName)).thenReturn(profile);
//		when(profileService.updateProfile(fullName,profile)).thenReturn(profile);
//		
//		
//		ResponseEntity<UserProfile> result= profileResource.updateProfile(fullName,profile);
//		
//		assertEquals(HttpStatus.OK, result.getStatusCode());
//		assertEquals("shivam", result.getBody().getFullName());
//		assertEquals(1, result.getBody().getProfileId());
//	}
//	
//	@Test
//	@Order(5)
//	public void test_deleteProfile() {
//			
//		profile=new  UserProfile(1, "shivam", "cse.png", "gmail232@gmail.com", (long) 63873455, "about",
//				LocalDate.of(2018, 12, 31), "Male", "Merchant", "abc@123",null);
//		
//		int profileId=1;
//		
//		Map<String,Boolean> response= new HashMap<String, Boolean>();
//		response.put("Deleted", true);
//		
//		when(profileService.getByProfileId(profileId)).thenReturn(profile);
//		when(profileService.deleteProfile(profileId)).thenReturn(response);
//		Map<String,Boolean> result= profileResource.deleteProfile(profileId);
//		
//		
//		
//		assertEquals(response, result);
//	}
//	
}
