package com.todos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.entities.User;
import com.todos.repositories.UserDao;


@Service
public class UserServices {
	
	@Autowired
	UserDao userDao;
	
	public int checkAUser(String email) {
		
		return userDao.checkAUser(email);
		
	}
	public boolean saveUser(User user) {
		return userDao.saveUser(user);
		
	}
	public User getUserDetails(String email) {
		return userDao.getUserDetails(email);
				
	}

}
