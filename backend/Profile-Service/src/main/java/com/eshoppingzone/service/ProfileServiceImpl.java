package com.eshoppingzone.service;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshoppingzone.model.UserProfile;
import com.eshoppingzone.repository.ProfileRepository;
import com.eshoppingzone.exception.ProfileAlreadyExistsException;
import com.eshoppingzone.exception.ProfileNotFoundException;

//
@Service
public class ProfileServiceImpl implements ProfileService {
	
	Logger logger= LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;
	
	
	//adding new customer profile
	@Override
	public UserProfile addNewCustomerProfile(UserProfile userProfile) {
		
		if(profileRepository.existsById(userProfile.getProfileId())) {
			logger.error("profile alerady exception occured while adding new customer");
			throw new ProfileAlreadyExistsException();
			
		}
		
		userProfile.setProfileId(seqService.getSequenceNum(UserProfile.SEQUENCE_NAME));
		
		return profileRepository.save(userProfile);

	}
	
	//listing all profiles
	@Override
	public List<UserProfile> getAllProfiles() {
		
		return profileRepository.findAll();
		
	}
	
	//get profile using individual profileId
	@Override
	public UserProfile getByProfileId(int profileId)throws ProfileNotFoundException{

			UserProfile profile;
			if(profileRepository.findById(profileId).isEmpty()) {
				logger.error("profile not found exception occured while adding new customer");
				throw new ProfileNotFoundException();
				
			}
			else {
				profile= profileRepository.findById(profileId).get();
			}
			
			return profile;

	}
	
	//delete profile by individual profileId
	@Override
	public Map<String,Boolean> deleteProfile(int profileId) throws ProfileNotFoundException {
		
		UserProfile profile = profileRepository.findById(profileId).orElseThrow(()->
			new ProfileNotFoundException());
		
		profileRepository.delete(profile);
		
		Map<String, Boolean> response = new HashMap<>();
		
		response.put("Deleted", Boolean.TRUE);
		return response;

	}

	@Override
	public void addNewMerchantProfile(UserProfile userProfile) {
		
		 profileRepository.save(userProfile);
	}
	
	//not using this method
	@Override
	public void addNewDeliveryProfile(UserProfile userProfile) {
		
		 profileRepository.save(userProfile);
	}
	
	//get profile By individual mobile Number
	@Override
	public UserProfile findByMobileNo(Long mobileNumber) {
		
		return profileRepository.findByMobileNumber(mobileNumber);

	}

	//get profile by individual  fullName
	@Override
	public UserProfile getByUserName(String fullName) {
		logger.info("find by name hit");
		return profileRepository.findByFullName(fullName);
	}

	
	//update  profile by individual fullName
	public UserProfile updateProfile( String fullName,UserProfile userProfile) {
		
		logger.info("profile update hit");
		UserProfile updatedProfile= profileRepository.findByFullName(fullName);
		
		
		updatedProfile.setAbout(userProfile.getAbout());
		updatedProfile.setAddress(userProfile.getAddress());
		updatedProfile.setDateOfBirth(userProfile.getDateOfBirth());
		updatedProfile.setEmailId(userProfile.getEmailId());
		updatedProfile.setGender(userProfile.getGender());
		updatedProfile.setImage(userProfile.getImage());
		updatedProfile.setMobileNumber(userProfile.getMobileNumber());
		updatedProfile.setPassword(userProfile.getPassword());
		updatedProfile.setRole(userProfile.getRole());
		
		final UserProfile updatedYourProfile=profileRepository.save(updatedProfile);
		 
		return updatedYourProfile;
		
		
	}

}
