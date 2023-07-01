package com.eshoppingzone.repository;

import org.springframework.data.mongodb.repository.MongoRepository; 
import org.springframework.stereotype.Repository;

import com.eshoppingzone.model.UserProfile;

@Repository
public interface ProfileRepository extends MongoRepository<UserProfile, Integer> {
	
	public UserProfile findByMobileNumber(Long mobileNumber);

	public UserProfile findByFullName(String fullName);
	
	

}
