package com.tek.lms.list;

import static com.tek.lms.list.ValidationUtils.*;
import java.util.ArrayList;
import java.util.List;

class Library {

	List<Book> books = new ArrayList<>();

	public void add(String id, String title, float price, String author) {
		if (!validateStringInput(id) || !validateStringInput(title) || !validateStringInput(author)
				|| !validateNumericInput(price)) {
			throw new IllegalArgumentException();
		}
		books.add(new Book(id, title, price, author));
		System.out.println("Book added successfully.");
	}

	public void reserve(String title) throws BookNotAvailableException {
		if (!validateStringInput(title)) {
			throw new IllegalArgumentException("Invalid Book Title type");
		}
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title) && book.getStatus() == BookStatus.AVAILABLE) {

				book.setStatus(BookStatus.BOOKED);

				System.out.println("""
						Borrowed: %s
						""".formatted(book.getTitle()));
				return;
			}
		}
		throw new BookNotAvailableException("Book not available.");
	}

	public Book remove(String id) {
		if (!validateStringInput(id)) {
			throw new IllegalArgumentException("ID passed is not of the right format.");
		}
		for (Book book : books) {
			if (book.getId().equalsIgnoreCase(id)) {
				books.remove(book);
				System.out.println("""
						Removed: %s
						""".formatted(book.getTitle()));
				return book;
			}
		}
		throw new BookNotAvailableException("No book found for ID: " + id);
	}

	public List<Book> find(String title) {
		List<Book> found = new ArrayList<>();
		String t = title.toLowerCase();

		for (Book b : books) {
			if (b.getTitle().toLowerCase().contains(t)) {
				found.add(b);
			}
		}
		return found;
	}

	public void markDamaged(String id) {
		for (Book b : books) {
			if (b.getId().equalsIgnoreCase(id)) {
				b.setStatus(BookStatus.DAMAGED);
				System.out.println("""
						Marked damaged: %s
						""".formatted(b.getTitle()));
				return;
			}
		}
		throw new BookNotAvailableException("No book found for ID: " + id);
	}

	public void returnBook(String id) {
		for (Book b : books) {
			if (b.getId().equalsIgnoreCase(id)) {

				if (b.getStatus() == BookStatus.BOOKED) {
					b.setStatus(BookStatus.AVAILABLE);
					System.out.println("""
							Returned: %s
							""".formatted(b.getTitle()));
				} else {
					System.out.println("""
							Book was not borrowed.
							""");
				}

				return;
			}
		}
		throw new BookNotAvailableException("No book found for ID: " + id);
	}

	public void displayAvailable() {
		System.out.println("""
				AVAILABLE BOOKS
				=========================
				""");

		for (Book b : books) {
			if (b.getStatus() == BookStatus.AVAILABLE) {
				System.out.println(b);
			}
		}
	}

	public void displayAll() {
		System.out.println("""
				ALL BOOKS
				=========================
				""");

		for (Book b : books) {
			System.out.println(b);
		}
	}
}
