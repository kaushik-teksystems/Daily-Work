package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Note;
import com.example.demo.services.NotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {

	@Autowired
	NotesService notesService;

	@GetMapping
	Iterable<Note> getNotes() {
		return notesService.getNotes();
	}

	@PostMapping
	Note addNote(@RequestBody @Valid Note note) {
		return notesService.createNote(note);
	}
	
	@DeleteMapping("/{id}")
	public void deleteNote(@PathVariable Long id) {
		notesService.deleteNote(id);
	}
}