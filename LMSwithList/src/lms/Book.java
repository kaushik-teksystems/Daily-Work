package lms;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private int id;
	private String title;
	private String author;
	private double price;
	private int availableCopies;

	private BookStatus status;

	private List<String> reservationList = new ArrayList<>();

	public Book(int id, String title, String author, double price, int availableCopies) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.availableCopies = availableCopies;

		this.status = availableCopies > 0 ? BookStatus.AVAILABLE : BookStatus.RESERVED;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public List<String> getReservationList() {
		return reservationList;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void addReservation(String user) {
		if (!reservationList.contains(user)) {
			reservationList.add(user);
			updateStatus();
		}
	}

	public void removeReservation(String user) {
		reservationList.remove(user);
		updateStatus();
	}

	public boolean bookCopy() {
		if (availableCopies > 0) {
			availableCopies--;
			updateStatus();
			return true;
		}
		return false;
	}

	public void returnCopy() {
		availableCopies++;
		updateStatus();
	}

	private void updateStatus() {
		if (availableCopies > 0 && reservationList.isEmpty()) {
			status = BookStatus.AVAILABLE;
		} else if (availableCopies == 0 && !reservationList.isEmpty()) {
			status = BookStatus.RESERVED;
		} else if (availableCopies > 0 && !reservationList.isEmpty()) {
			status = BookStatus.BOOKED;
		}
	}

	@Override
	public String toString() {
		return String.format("[%d] %s by %s | ₹%.2f | Copies: %d | Status: %s", id, title, author, price,
				availableCopies, status);
	}
}