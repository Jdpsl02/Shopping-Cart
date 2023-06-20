package com.eshoppingzone.profile.UserProfileService.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eshoppingzone.profile.UserProfileService.pojo.UserProfile;
import com.eshoppingzone.profile.exception.ProfileAlreadyExistsException;
import com.eshoppingzone.profile.exception.ProfileNotFoundException;

//interface for profile service implementation
@Service
public interface ProfileService {

	public UserProfile addNewCustomerProfile(UserProfile userProfile) throws ProfileAlreadyExistsException;

	public List<UserProfile> getAllProfiles();

	public UserProfile getByProfileId(int profileId)throws ProfileNotFoundException;

	public Map<String,Boolean>  deleteProfile(int profileId) throws ProfileNotFoundException;

	public void addNewMerchantProfile(UserProfile userProfile);

	public void addNewDeliveryProfile(UserProfile userProfile);

	public UserProfile findByMobileNo(Long mobileNumber);

	public UserProfile getByUserName(String fullName);

	public  UserProfile updateProfile( String fullName,UserProfile userProfile) throws ProfileNotFoundException;

}
