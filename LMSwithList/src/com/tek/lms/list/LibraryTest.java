package com.tek.lms.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {
	Library library;

	@BeforeEach
	void setup() {
		library = new Library();
	}

	@Test
	void testSuccessfullBookReservation() {
		Book book = new Book("1", "Learn Java", 599.9F, "Kaushik Parida");
		library.books.add(book);
		library.reserve("Learn Java");
		assertEquals(BookStatus.BOOKED, book.getStatus());
	}

	@Test
	void testReserveWhenBookTitleIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			library.reserve(null);
		});
	}

	@Test
	void testReserveWhenBookTitleIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> {
			library.reserve("");
		});
	}

	@Test
	void testReserveWhenBookNotAvailable() throws BookNotAvailableException {
		assertThrows(BookNotAvailableException.class, () -> {
			library.reserve("Learn Java");
		});
	}

	@Test
	void testSuccessfulAdditionOfBook() {
		Book book = new Book("2", "Learn Python", 499.9F, "ABC");
		library.books.add(book);
		assertEquals(BookStatus.AVAILABLE, book.getStatus());
	}

	@Test
	void testRemoveWhenIdIsNull() {
		assertThrows(BookNotAvailableException.class, () -> {
			library.remove(null);
		});
	}

	@Test
	void testSuccessfulRemovalOfBook() {
		Book book = new Book("2", "Learn Python", 799.9F, "Kaushik");
		library.books.remove("2");
		assertThrows(BookNotAvailableException.class, () -> {
			library.returnBook("2");
		});
	}

}
