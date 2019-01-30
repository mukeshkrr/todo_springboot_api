package com.todos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.entities.Notes;
import com.todos.services.NotesServices;

@RestController
@RequestMapping("/notes")
public class NotesController {
	
	@Autowired
	NotesServices notesServices;
	
	@PostMapping("/add-note")
	public Map<String,Object> addANote(@RequestBody Notes notes) {
		
		Map<String,Object> map = new HashMap<>();
		boolean st = notesServices.saveNote(notes);
		if(st) {
			map.put("status", "success");
			map.put("message", "You have added a note successfully");
		}
		else {
			
			map.put("status", "error");
			map.put("message", "Same note already exists...");
			
		}
		return map;
		
	}
	@GetMapping("/get-notes/{uid}")
	public List<Notes> getAllNotes(@PathVariable  int uid) {
			
			
			return notesServices.getAllNotes(uid);
			
		}
	
	@GetMapping("/get-a-note/{uid}/{id}")
	public Notes getANote(@PathVariable  int uid, @PathVariable  int id) {
			
			
			return notesServices.getANote(id, uid);
			
		}
	
	@DeleteMapping("/delete/{uid}/{id}")
	public Map<String,Object> deleteANote(@PathVariable  int uid, @PathVariable  int id){
		Map<String,Object> map = new HashMap<>();
		if(notesServices.deleteNote(uid, id)) {
			
			map.put("status", "success");
			map.put("message", "You have deleted a note successfully");
			
		}
		else {
			map.put("status", "error");
			map.put("message", "Error found. Try again later...");
		}
		return map;
	}
	
	@DeleteMapping("/all-delete/{uid}")
	public Map<String,Object> deleteANote(@PathVariable  int uid){
		Map<String,Object> map = new HashMap<>();
		if(notesServices.deleteAllNote(uid)) {
			
			map.put("status", "success");
			map.put("message", "You have deleted all notes successfully");
			
		}
		else {
			map.put("status", "error");
			map.put("message", "Error found. Try again later...");
		}
		return map;
	}
	
	@PutMapping("/is-completed/{uid}/{id}/{st}")
	public Map<String,Object> changeNoteStatus(@PathVariable  int uid, @PathVariable  int id, @PathVariable  int st) {
		
		Map<String,Object> map = new HashMap<>();
		boolean status = notesServices.isCompleted(st, uid, id);
		if(status) {
			map.put("status", "success");
			map.put("message", "You have changed status of the note successfully");
		}
		else {
			
			map.put("status", "error");
			map.put("message", "Error found. Try again later...");
			
		}
		return map;
		
	}
	

}
