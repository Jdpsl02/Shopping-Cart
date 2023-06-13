package com.example.jwt;

import java.io.IOException; 
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {
  @Autowired
  private TokenUtility jwtUtils;

  
  private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
  
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  Logger log = LoggerFactory.getLogger(TokenFilter.class);
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
	  logger.debug("=============== filter =================");
	//  logger.debug(request.getHeaderNames());
	  Enumeration<String> headerNames = request.getHeaderNames();
	  while(headerNames.hasMoreElements()) {
	    String headerName = headerNames.nextElement();
	    logger.debug("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
	  }
      String token = request.getHeader("Authorization");
//	  String token =null;
      if (token != null && token.startsWith("Bearer ")) {
          token = token.substring(7);
          if (!jwtUtils.validateToken(token)) {
        	  logger.debug("=============== valid token =================");
              String username = jwtUtils.getUsernameFromToken(token);
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetailsService.loadUserByUsername(username), null, userDetailsService.loadUserByUsername(username).getAuthorities());
              logger.info("authtoken: {}",authenticationToken);
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
              
          }
          else {
        	  logger.debug("=============== invalid token =================");
          }
      }
      else {
    	  logger.debug("=============== token is null =================");
      }
      filterChain.doFilter(request, response);
  }
  

 
//  @Override
//  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//      throws ServletException, IOException {
//	  
//	  String authHeader = request.getHeader("Authorization");
//      if(authHeader != null && authHeader.startsWith("Bearer")){
//          String jwt = authHeader.substring(7);
//          if(jwt == null || !jwtUtils.validateToken(jwt)){
//              response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
//          }else {
//              try{
//            	  Claims claims = jwtUtils.getClaim(jwt);
//                  String username = String.valueOf(claims.get("username"));
//				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                  UsernamePasswordAuthenticationToken authToken =
//                          new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
//                  if(SecurityContextHolder.getContext().getAuthentication() == null){
//                      SecurityContextHolder.getContext().setAuthentication(authToken);
//                  }
//              }catch(Exception e){
//            	  logger.error("Cannot set user authentication: {}", e);
//              }
//          }
//      }
//
//      filterChain.doFilter(request, response);
//  }

  
}
