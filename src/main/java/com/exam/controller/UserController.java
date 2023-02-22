package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200/")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> roles=new HashSet<>();
		user.setPassword(bCrypt.encode(user.getPassword()));
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		UserRole userrole=new UserRole();
		userrole.setRole(role);
		userrole.setUser(user);
		roles.add(userrole);
		return this.us.createUser(user, roles);
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.us.getUser(username);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		this.us.deleteUser(id);
	}
}
