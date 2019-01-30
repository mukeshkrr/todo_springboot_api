package com.todos.model;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todos.entities.User;
import com.todos.repositories.UserDao;

@Repository
public class UserImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		//return sessionFactory.getCurrentSession();
		try {
		    return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    return sessionFactory.openSession();
		}
	}

	@Override
	public int checkAUser(String email) {
		
		
		int count = getSession().createQuery("from User WHERE email = :email").setParameter("email", email).getResultList().size();
		System.out.println("***");
		System.out.println(count);
		System.out.println("****");
		
		return count;
		
		
	}

	@Override
	public boolean saveUser(User user) {
		
		if(checkAUser(user.getEmail()) == 0) {
			int isSuccess = (int) getSession().save(user);
			if(isSuccess > 0) return true;
		}
		return false;
		
		
	}

	@Override
	public User getUserDetails(String email) {
		Query q = getSession().createQuery("from User WHERE email = :email");
		q.setParameter("email", email);
		User u = new User();
		try {
			u = (User) q.getSingleResult();
		}
		catch(Exception e) {
			
		}
		return u;
		
	}

}
