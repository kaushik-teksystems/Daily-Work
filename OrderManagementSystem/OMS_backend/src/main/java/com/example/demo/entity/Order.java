package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "orders")
public class Order {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	@NotBlank(message = "Customer name is required")
	private String customerName;
	@NotBlank(message = "Address is required")
	private String address;
	
	private String status;
	
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	@Valid
	@NotEmpty(message = "Order must contain at least one item")
	private List<OrderLine> orderLines = new ArrayList<>();

	public void addOrderLine(OrderLine line) {
		orderLines.add(line);
		line.setOrder(this);
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines.clear();
		if (orderLines != null) {
			for (OrderLine line : orderLines) {
				addOrderLine(line);
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
}