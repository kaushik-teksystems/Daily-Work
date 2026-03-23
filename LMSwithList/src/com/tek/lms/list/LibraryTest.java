package com.tek.lms.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {
	Library library;

	@BeforeEach
	void setup() {
		library = new Library();
	}

	// Test cases for Reserve
	@Test
	void testSuccessfullBookReservation() {
		Book book = new Book("1", "Learn Java", 599.9F, "Kaushik Parida");
		library.books.add(book);
		library.reserve("Learn Java");
		assertEquals(BookStatus.BOOKED, book.getStatus());
	}

	@Test
	void testReserveWhenBookTitleIsNull() {
		assertThrows(IllegalArgumentException.class, () -> library.reserve(null));
	}

	@Test
	void testReserveWhenReservedWithEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> library.reserve(""));
	}

	@Test
	void testReserveWhenTitleHasBlankSpaces() {
		assertThrows(IllegalArgumentException.class, () -> library.reserve("     "));
	}

	@Test
	void testReserveWhenBookNotAvailable() {
		assertThrows(BookNotAvailableException.class, () -> library.reserve("Learn Java"));
	}

	// Test Cases For Adding Books
	@Test
	void testSuccessfulAdditionOfBook() {
		Book book = new Book("1", "Learn Java", 599.9F, "Kaushik Parida");
		library.books.add(book);
		library.add("1", "Learn Java", 599.9F, "Kaushik Parida");
		assertTrue(library.find("Learn Java").contains(book));
	}

	@Test
	void testAdditionWhenArgumentsAreNull() {
		assertThrows(IllegalArgumentException.class, () -> library.add(null, null, 0, null));
	}

	@Test
	void testAdditionWhenIDIsPassedAsEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> library.add("", "Learn Java", 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenIdIsNull() {
		assertThrows(IllegalArgumentException.class, () -> library.add(null, "Learn Java", 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenIdIsPassedWithBlankSpaces() {
		assertThrows(IllegalArgumentException.class, () -> library.add("   ", "Learn Java", 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenTitleIsNull() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", null, 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenTitleIsPassedAsEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "", 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenTitleIsPassedAsBlankSpaces() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "     ", 599.9F, "Kaushik Parida"));
	}

	@Test
	void testAdditionWhenAuthorIsNull() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "", 599.9F, null));
	}

	@Test
	void testAdditionWhenAuthorIsPassedAsEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "", 599.9F, ""));
	}

	@Test
	void testAdditionWhenAuthorIsPassedAsBlankSpaces() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "", 599.9F, "    "));
	}

	@Test
	void testAdditionWithNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> library.add("1", "Learn Java", -599.9F, "Kaushik Parida"));
	}

	// Test Cases for Removal of books
	@Test
	void testSuccessfulRemovalOfBook() {
		Book book = new Book("1", "Learn Java", 599.9F, "Kaushik Parida");
		library.books.add(book);
		library.remove("1");
		assertThrows(BookNotAvailableException.class, () -> library.remove("2"));
	}

	@Test
	void testRemoveBookWhenIdIsNULL() {
		assertThrows(IllegalArgumentException.class, () -> library.remove(null));
	}

	@Test
	void testRemoveBookWhenIdIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> library.remove(""));
	}

	// Test For DamagedBooks
	@Test
	void testSuccessfulDamagedBookMarking() {
		Book book = new Book("2", "Learn Python", 499.9F, "Alex");
		library.books.add(book);
		library.markDamaged("2");
		assertEquals(BookStatus.DAMAGED, book.getStatus());
	}
	
	@Test
	void testMarkDamageWhenBookNotAvailable() {
		assertThrows(BookNotAvailableException.class, ()-> library.markDamaged("1"));
	}

	
	//Test For Book Return Process
	@Test
	void testSuccessfulReturn() {
		Book book = new Book("3", "Learn Go", 799.9F, "Rem");
		library.books.add(book);
		library.reserve("Learn Go");
		library.returnBook("3");
		assertEquals(BookStatus.AVAILABLE, book.getStatus());
	}
	
	@Test
	void testReturnWhenBookWasNotBorrowed() {
		assertThrows(BookNotAvailableException.class, ()-> library.returnBook("3"));
	}
}
