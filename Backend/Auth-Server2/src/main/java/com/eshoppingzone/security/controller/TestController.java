package com.eshoppingzone.security.controller;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.eshoppingzone.security.models.User;
import com.eshoppingzone.security.models.UserProfile;
import com.eshoppingzone.security.payload.request.SignupRequest;
import com.eshoppingzone.security.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	Logger logger= LoggerFactory.getLogger(TestController.class);
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PasswordEncoder encoder;

	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	
	@PutMapping("/user/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<String> updateYourProfile(@RequestBody SignupRequest signupRequest) {
		
		logger.info("data is"+signupRequest);
	User user=	userRepository.findByUsername(signupRequest.getFullName()).orElseThrow(()-> new RuntimeException("User not found"));
		
		user.setEmail(signupRequest.getEmailId());
		user.setUsername(signupRequest.getFullName());
		user.setPassword(encoder.encode(signupRequest.getPassword()) );
		
		
		userRepository.save(user);
		
		restTemplate.put("http://profile-service/profile/user/update/"+signupRequest.getFullName(),signupRequest, UserProfile.class);
		
		return ResponseEntity.ok("Profile update with useranme"+signupRequest.getFullName());

	}


@DeleteMapping("/user/delete/{id}/{fullName}")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public ResponseEntity<String> deleteYourProfile(@PathVariable(value = "id") int id,@PathVariable(value="fullName") String fullName) {
	
	logger.info("auth delete method hit by user" + fullName);
	
 User deleteUser=	userRepository.findByUsername(fullName).orElseThrow(()-> new RuntimeException("User not found"));
 
 userRepository.delete(deleteUser);


	restTemplate.delete("http://profile-service/profile/user/delete/"+id);
	
	return ResponseEntity.ok("Profile is deleted with useranme"+fullName);

}
}