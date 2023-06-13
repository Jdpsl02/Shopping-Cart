package com.example;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Users;
import com.example.Users.Role;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository repository;
	 public void register(RegisterRequest request) {
		 Users user = new Users();
		 user.setUsername(request.getUsername());
		 user.setPassword(passwordEncoder.encode(request.getPassword()));
		 if(request.getRole().equalsIgnoreCase("admin"))
		      user.setRole(Role.ADMIN);
		 else
			 user.setRole(Role.USER);
		 
		    		
//		    		(Users) User.builwdder()
//		        .username(request.getUsername())
//		        .password(passwordEncoder.encode(request.getPassword()))
//		        .roles("USER")
//		        .build();
		    if (user!=null) {
			    repository.save((Users)user);
			}
		
		  }
	
}
