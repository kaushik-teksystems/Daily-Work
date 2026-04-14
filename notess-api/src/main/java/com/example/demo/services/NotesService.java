package com.example.demo.services;

import com.example.demo.repository.NotesRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Note;

@Service
public class NotesService {

	@Autowired
	NotesRepository notesRepository;

	public Iterable<Note> getNotes() {
		return notesRepository.findAll();
	}

	public Note createNote(@RequestBody @Valid Note note) {
		return notesRepository.save(note);
	}
	
	public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }
	
	public Note updateNote(Long id, Note updatedNote) {
		Note existingNote = notesRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
		
		if("closed".equalsIgnoreCase(existingNote.getStatus())) {
			throw new IllegalStateException("Can't update a closed note, try adding a separate note");
		}
		
		if(updatedNote.getStatus() != null) {
			existingNote.setStatus(updatedNote.getStatus());
		}
		
		return notesRepository.save(existingNote);
	}
}