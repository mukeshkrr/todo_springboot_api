package com.todos.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.entities.Notes;
import com.todos.repositories.NotesDao;


@Service
public class NotesServices {
	
	@Autowired
	NotesDao notesDao;
	
	@Transactional
	public int checkNoteExist(String note, int uid) {
		
		return notesDao.checkNoteExist(note, uid);
		
	}
	@Transactional
	public boolean saveNote(Notes note) {
		return notesDao.saveNote(note);
		
	}
	@Transactional
	public List<Notes> getAllNotes(int uid){
		
		return notesDao.getAllNotes(uid);
		
	}
	@Transactional
	public Notes getANote(int id, int uid) {
		return notesDao.getANote(id, uid);
		
	}
	@Transactional
	public boolean deleteNote(int uid, int noteId) {
		
		return notesDao.deleteNote(uid, noteId);
		
	}
	@Transactional
	public boolean deleteAllNote(int uid) {
		return notesDao.deleteAllNote(uid);
	}
	@Transactional
	public boolean isCompleted(int st, int uid, int noteId) {
		return notesDao.isCompleted(st, uid, noteId);
	}

}
