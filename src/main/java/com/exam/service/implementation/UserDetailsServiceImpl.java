package com.exam.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepo ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u= this.ur.findByUsername(username);
		if(u==null) {
			System.out.println("No username");
			throw new UsernameNotFoundException("No username matched");
		}
		return u;
	}

}
