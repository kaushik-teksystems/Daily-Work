package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Order;

public interface IOrderService {
	Iterable<Order> getOrder();
	Integer addOrder(Order order);
	Optional<Order> getOrderById(Integer id);

}
