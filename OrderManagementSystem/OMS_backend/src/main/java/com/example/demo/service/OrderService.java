package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderLine;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderRepository;
//	@Autowired
//	PaymentService paymentSerice;
//	@Autowired
//	EmailService emailService;

	public Iterable<Order> getOrder() {
		return orderRepository.findAll();
	}

	public Integer addOrder(Order order) {
		if (order.getOrderLines() == null || order.getOrderLines().isEmpty()) {
			throw new IllegalArgumentException("Order must have at least one item");
		}

		order.getOrderLines().forEach(line -> line.setOrder(order));
		order.setStatus("CREATED");
		order.setCreatedAt(LocalDateTime.now());

		orderRepository.save(order);
		return order.getId();
	}

	public Optional<Order> getOrderById(Integer id) {
		return orderRepository.findById(id);
	}

	public void deleteOrder(Integer id) {
		orderRepository.deleteById(id);
	}

}
