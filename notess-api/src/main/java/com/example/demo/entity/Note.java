package com.example.demo.entity;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Note {
	@Id
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@Min(1)
	@Max(3)
	private int priority = 2;
	
	private String timeRequired = "0h 0m";
	
	private String status = "created";
	
	private String createdAt;
	
	@PrePersist
	protected void onCreate() {
		if(this.id == null) {
			this.id = System.currentTimeMillis();
		}			
		if(this.createdAt == null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy, h:mm:ss a");
			this.createdAt = java.time.LocalDateTime
					.now().format(formatter);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
}

enum STATUS {
	CLOSED, CREATED;
}