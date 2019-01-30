package com.todos.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.entities.User;
import com.todos.repositories.PasswordEncoder;
import com.todos.repositories.UserDao;
import com.todos.services.UserServices;


@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserServices userServices;
	
	@PostMapping("/save")
	public Map<String,Object> save(@RequestBody User user){
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Map<String,Object> map = new HashMap<>();
		boolean st = userServices.saveUser(user);
		if(st) {
			map.put("status", "success");
			map.put("message", "You are registered successfully");
		}
		else {
			
			map.put("status", "error");
			map.put("message", "Email Address already exists...");
			
		}
		return map;
		
	}
	
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody User user){
		Map<String,Object> map = new HashMap<>();
		User u = userServices.getUserDetails(user.getEmail());
		
		if(u.getEmail() == null || u.getPassword() == null) {
			map.put("status", "error");
			map.put("message", "Email ID or Password doesn't exists");
		}
		else {
			
			String encodePass = u.getPassword();
			String password = user.getPassword();
			System.out.println(u);
			System.out.println(user);
			if(encodePass != null && password != null) {
				Boolean checkPass = passwordEncoder.matches(password, encodePass);
				if(checkPass) {
					
					map.put("status", "success");
					map.put("message", "Logged in successfully");
					map.put("data", u);
					
					
					
				}
				else {
					map.put("status", "error");
					map.put("message", "Wrong password");
				}
			}
			else {
				map.put("status", "error");
				map.put("message", "Enter a password");
			}
			
		}
		
		return map;
		
	}

}
