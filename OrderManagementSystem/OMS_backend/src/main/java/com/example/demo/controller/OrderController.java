package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.OmsBackendApplication;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OmsBackendApplication omsBackendApplication;
	@Autowired
	OrderService orderService;

	OrderController(OmsBackendApplication omsBackendApplication) {
		this.omsBackendApplication = omsBackendApplication;
	}

	@GetMapping("/{id}")
	Optional<Order> getOrderById(@PathVariable Integer id) {
		return orderService.getOrderById(id);
	}

	@GetMapping()
	Iterable<Order> getOrder() {
		return orderService.getOrder();
	}

	@PostMapping()
	Integer createOrder(@RequestBody @Valid Order order) {
		return orderService.addOrder(order);
	}

	@DeleteMapping("/{id}")
	void deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
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
