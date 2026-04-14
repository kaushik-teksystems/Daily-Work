package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Note;

@Repository
public interface NotesRepository extends CrudRepository<Note, Long>{

}
