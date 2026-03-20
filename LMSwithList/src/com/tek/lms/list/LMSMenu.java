package com.tek.lms.list;

import java.util.List;
import java.util.Scanner;

public class LMSMenu {

	private Library library;
	private Scanner sc = new Scanner(System.in);

	public LMSMenu(Library library) {
		this.library = library;
	}

	void showMenu() {
		System.out.println("""

				Library Management System
				1. Add Book
				2. Remove Book
				3. Reserve Book
				4. Display Available Books
				5. Display All Books
				6. Find Books
				7. Mark Damaged
				8. Return Book
				0. Exit
				""");
	}

	public void start() {
		while (true) {
			try {
				showMenu();
				System.out.print("Enter choice: ");

				int choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1 -> addBook();
				case 2 -> removeBook();
				case 3 -> reserveBook();
				case 4 -> library.displayAvailable();
				case 5 -> library.displayAll();
				case 6 -> findBooks();
				case 7 -> markDamaged();
				case 8 -> returnBook();
				case 0 -> {
					System.out.println("Exiting...");
					return;
				}
				default -> System.out.println("Invalid choice.");
				}

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

	private void addBook() {
		System.out.print("Title: ");
		String title = sc.nextLine();

		System.out.print("Author: ");
		String author = sc.nextLine();

		System.out.print("ID: ");
		String id = sc.nextLine();

		System.out.print("Price: ");
		float price = sc.nextFloat();
		sc.nextLine();

		library.add(id, title, price, author);
	}

	private void removeBook() throws BookNotAvailableException {
		System.out.print("Enter ID: ");
		String id = sc.nextLine();
		library.remove(id);
	}

	private void reserveBook() throws BookNotAvailableException {
		System.out.print("Enter title: ");
		String title = sc.nextLine();
		library.reserve(title);
	}

	private void findBooks() {
		System.out.print("Enter title: ");
		String title = sc.nextLine();

		List<Book> result = library.find(title);

		if (result.isEmpty()) {
			System.out.println("""
					No books found.
					""");
		} else {
			System.out.println("""
					MATCHED BOOKS
					=========================
					""");

			for (Book b : result) {
				System.out.println(b + "\n");
			}
		}
	}

	private void markDamaged() throws BookNotAvailableException {
		System.out.print("Enter ID: ");
		String id = sc.nextLine();
		library.markDamaged(id);
	}

	private void returnBook() throws BookNotAvailableException {
		System.out.print("Enter ID: ");
		String id = sc.nextLine();
		library.returnBook(id);
	}
}