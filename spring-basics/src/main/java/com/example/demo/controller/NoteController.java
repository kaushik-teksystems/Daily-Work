package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SpringBasicsApplication;
import com.example.demo.entity.Order1;
import com.example.demo.service.NoteService;

import jakarta.validation.Valid;

@RestController // default: JSON
@RequestMapping("/order")
public class NoteController {

	private final SpringBasicsApplication springBasicsApplication;// dependent
	@Autowired
	NoteService noteService;

	NoteController(SpringBasicsApplication springBasicsApplication) {
		this.springBasicsApplication = springBasicsApplication;
	}// dependency

	@GetMapping("/{id}")
	Optional<Order1> getOrderById(@PathVariable Integer id) {
		return noteService.getOrderById(id);
	}
	
	@GetMapping()
	Iterable<Order1> getOrder() {
		return noteService.getOrder();
	}

	@PostMapping()
	Integer createOrder(@RequestBody @Valid Order1 order1) throws IOException{
		System.out.println(order1.getPrice());
		return noteService.addOrder(order1);
	}
	
	@DeleteMapping("/{id}")
	void deleteOrder(@PathVariable Integer id) {
		noteService.deleteOrder(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;

	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
		public String httpMessageNotReadableException(HttpMessageNotReadableException ex) {
			return "Something went wrong, please Try Again";
		}
}
