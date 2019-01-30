package com.todos.repositories;


import com.todos.entities.User;


public interface UserDao {
	
	public int checkAUser(String email);
	public boolean saveUser(User user);
	public User getUserDetails(String email);

}
