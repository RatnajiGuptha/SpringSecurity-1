package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.implementation.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtils;
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
//	private void authenticate(String username,String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		}catch(DisabledException e) {
//			throw new Exception("User Denied");
//		}catch(BadCredentialsException e) {
//			throw new Exception("Invalid cred");
//		}
//	}
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}
}
