package com.tek.lms.list;
import static com.tek.lms.list.ValidationUtils.*;

class Book {

	private String id;
	private String title;
	private float price;
	private String author;
	private BookStatus status;

	public Book(String id, String title, float price, String author) {
		if(!validateStringInput(id) || !validateStringInput(title) || !validateStringInput(author)) {
			throw new IllegalArgumentException("Invalid Argument.");
		}
		if(!validateNumericInput(price))
			throw new IllegalArgumentException("Invalid Argument");
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
		this.status = BookStatus.AVAILABLE;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public float getPrice() {
		return price;
	}

	public String getAuthor() {
		return author;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return """
				ID: %s
				Book: %s
				Author: %s
				Price: %.2f
				Status: %s
				""".formatted(id, title, author, price, status);
	}
}