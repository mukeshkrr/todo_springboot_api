package com.todos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todos.entities.Notes;
import com.todos.repositories.NotesDao;

@Repository
public class NotesImpl implements NotesDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		//return sessionFactory.getCurrentSession();
		try {
		    return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    return sessionFactory.openSession();
		}
	}

	@Override
	public int checkNoteExist(String note, int uid) {
		

		int count = getSession().createQuery("from Notes WHERE text = :note AND uid = :uid").setParameter("note", note).setParameter("uid", uid).getResultList().size();
		
		return count;
		
	}

	@Override
	public boolean saveNote(Notes note) {
		if(checkNoteExist(note.getText(), note.getUid()) == 0) {
			int isSuccess = (int) getSession().save(note);
			if(isSuccess > 0) return true;
		}
		return false;
		
	}

	

	@Override
	public boolean deleteNote(int uid, int noteId) {
		Query q = getSession().createQuery("delete from Notes WHERE id = :id AND uid = :uid").setParameter("id", noteId).setParameter("uid", uid);
		try {
			int count = q.executeUpdate();		
			if(count > 0) return true;
		}
		catch(Exception e) {
			
		}
		return false;
		
	}

	@Override
	public boolean deleteAllNote(int uid) {
		Query q = getSession().createQuery("delete from Notes WHERE  uid = :uid").setParameter("uid", uid);
		try {
			int count = q.executeUpdate();		
			if(count > 0) return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}

	@Override
	public boolean isCompleted(int st, int uid, int noteId) {
		Query q = getSession().createQuery("UPDATE Notes SET isCompleted = :st WHERE  uid = :uid AND id = :id")
				.setParameter("st", st)
				.setParameter("uid", uid)
				.setParameter("id", noteId);
		try {
			int count = q.executeUpdate();		
			if(count > 0) return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notes> getAllNotes(int uid) {
		List<Notes> li = new ArrayList<>();
		Query q = getSession().createQuery("from Notes WHERE uid = :uid");
		q.setParameter("uid", uid);
		try {
			li = q.getResultList();
		}
		catch(Exception e) {
			
		}
		return li;
	}

	@Override
	public Notes getANote(int id, int uid) {
		Query q = getSession().createQuery("from Notes WHERE  uid = :uid AND id = :id");
		q.setParameter("uid", uid);
		q.setParameter("id", id);
		Notes note = new Notes();
		try {
			note =  (Notes)q.getSingleResult();
		}
		catch(Exception e) {
			
		}
		return note;
	}

}
