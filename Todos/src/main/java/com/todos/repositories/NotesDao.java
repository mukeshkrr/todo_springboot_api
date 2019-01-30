package com.todos.repositories;

import java.util.List;

import com.todos.entities.Notes;

public interface NotesDao {

	public int checkNoteExist(String note, int uid);
	public boolean saveNote(Notes note);
	public List<Notes> getAllNotes(int uid);
	public Notes getANote(int id, int uid);
	public boolean deleteNote(int uid, int noteId);
	public boolean deleteAllNote(int uid);
	public boolean isCompleted(int st, int uid, int noteId);
}
