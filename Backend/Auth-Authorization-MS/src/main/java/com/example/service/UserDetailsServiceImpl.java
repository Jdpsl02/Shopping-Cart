package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.model.Login;
import com.example.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login userOp = repository.findByUsername(username);
		if (userOp == null) {
			throw new UsernameNotFoundException(
					"User Not Found with username: " + username);
		}
		else {
			return getUser(username, userOp);
		}
	}

	private UserDetails getUser(String username, Login user) {
		 
		return UserDetailsImpl.getUser(user);
	}

}
