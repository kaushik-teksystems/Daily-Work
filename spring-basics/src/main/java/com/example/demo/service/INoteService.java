package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Order1;

public interface INoteService {
	Iterable<Order1> getOrder();
	Integer addOrder(Order1 order1);
	Optional<Order1> getOrderById(Integer id);
}
