package com.exam.service.implementation;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.service.UserService;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepo;
import com.exam.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private RoleRepo rr;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		User u=this.ur.findByUsername(user.getUsername());
		if(u!=null || user.getUsername()==null || user.getUsername()=="") {
			throw new Exception("User already exists");
		}
		else {
			for(UserRole role:userRoles) {
				rr.save(role.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			u=this.ur.save(user);
		}
		return u;
	}

	@Override
	public User getUser(String username) {
		return ur.findByUsername(username);
	}
	@Override
	public void deleteUser(long id) {
		ur.deleteById(id);
	}
	
}
