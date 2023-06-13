package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.TokenUtility;
import com.example.repository.UserRepository;
import com.example.request.LoginRequest;
import com.example.response.JSONResponse;
import com.example.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

	@Autowired
	private TokenUtility jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	UserRepository repository;

	org.slf4j.Logger log = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signin")
	public ResponseEntity<?> validateUser( @RequestBody LoginRequest loginRequest) {
//		setTable();

		log.debug("************** inside signin" );
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());

			String token = jwtTokenUtil.generateToken(authentication);
			log.debug("************** token: " + token);
			List<String> roles = new ArrayList<>();
			for (GrantedAuthority authority : userDetails.getAuthorities()) {
				roles.add(String.valueOf(authority));
			}
			JSONResponse jsonResponse = new JSONResponse(token, userDetails.getUsername(), roles);

			return ResponseEntity.ok(jsonResponse);
		} catch (Exception authExc) {
			throw new RuntimeException(authExc.getMessage());
		}

	}

}
