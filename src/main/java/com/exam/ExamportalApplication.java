package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
@EnableAutoConfiguration
@CrossOrigin("*")
public class ExamportalApplication implements CommandLineRunner{
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
//		User user=new User();
//		user.setFirstname("tom");
//		user.setLastname("hell");
//		user.setUsername("tom123");
//		user.setPassword(this.bCrypt.encode("tom"));
//		user.setEmail("tom_hell@gmail.com");
//		user.setPhone("9846516516");
//		Role role=new Role();
//		role.setRoleId(44L);
//		role.setRoleName("Admin");
//		UserRole userRole=new UserRole();
//		Set<UserRole> set=new HashSet<>();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		set.add(userRole);
//		this.userService.createUser(user, set);
	}
}
