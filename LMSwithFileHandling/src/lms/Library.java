package lms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class Library {

	private List<Book> books = new ArrayList<>();
	public void readBooks() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("book_list.txt"));
			String line;
			while((line = reader.readLine())!= null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("Can't read because of unavailability or different format.");
		}
	}

	public void add(String id, String title, float price, String author) {
		books.add(new Book(id, title, price, author));
		try {
			BufferedWriter writeDetails = new BufferedWriter(new FileWriter("book_list.txt", true));
			for(Book book: books) {
				writeDetails.write(id+",");
				writeDetails.write(title+",");
				writeDetails.write(author+",");
				String priceString = String.valueOf(price);
				writeDetails.write(priceString);
				writeDetails.flush();
			}
		} catch (Exception e) {
			System.out.println("Nothing to add to te book_list");
		}
		System.out.println("Book added successfully.");
	}

	public void reserve(String title) throws BookNotAvailableException {
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

	public Book remove(String id) throws BookNotAvailableException {
		for (Book b : books) {
			if (b.getId().equalsIgnoreCase(id)) {
				books.remove(b);
				System.out.println("""
						Removed: %s
						""".formatted(b.getTitle()));
				return b;
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

	public void markDamaged(String id) throws BookNotAvailableException {
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

	public void returnBook(String id) throws BookNotAvailableException {
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
