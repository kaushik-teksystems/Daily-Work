package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order1;
import com.example.demo.repository.Order1Repository;

@Service 
public class NoteService {
	
	@Autowired
	Order1Repository order1Repository;
	
	public Iterable <Order1> getOrder(){
		return order1Repository.findAll();
	}
	
	public Integer addOrder(Order1 order1) {
		order1Repository.save(order1);
		return order1.getId();
	}
}
