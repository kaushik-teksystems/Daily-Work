package com.tek.lms.streams;

import java.util.stream.*;
import java.util.*;

public class LibraryService {
	private List<Book> books = new ArrayList<>();

	public void addBook(Book book) {
		books.add(book);
	}

	public void displayBooks() {
		books.stream().forEach(book -> System.out.println());
	}
	
	public void searchByTitle(String title) {
		books.stream()
			.filter(book -> book.getTitle()
			.toLowerCase()
			.contains(title.toLowerCase()))
			.forEach(System.out::println);
	}
	
	public void showAvailableBooks() {
		books.stream()
			.filter(book -> book.isAvailable())
			.forEach(System.out::println);
	}
	
	public void borrowBooks(int id) {
		books.stream()
			.filter(book -> book.getId() == id)
			.findFirst()
			.ifPresent(book -> book.borrowBook());
	}

    public void sortBooksByTitle() {
        books.stream()
             .sorted((b1,b2) -> b1.getTitle()
             .compareTo(b2.getTitle()))
             .forEach(System.out::println);
    }

    public long countAvailableBooks() {

        return books.stream()
                    .filter(Book::isAvailable)
                    .count();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

