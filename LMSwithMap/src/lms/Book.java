package lms;

public class Book {
	private final int id;
	private final String title;
	private final String author;
	private final double price;

	private int availableCopies;
	private BookStatus status;

	private String reservedBy;

	public Book(int id, String title, String author, double price, int availableCopies) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.availableCopies = availableCopies;

		updateStatus();
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

	public String getReservedBy() {
		return reservedBy;
	}

	public BookStatus getStatus() {
		return status;
	}

	public boolean bookCopy() {
		if (status == BookStatus.DAMAGED) {
			System.out.println("Book is damaged and cannot be issued.");
			return false;
		}
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

	public boolean placeHold(String user) {
		if (reservedBy == null) {
			reservedBy = user;
			updateStatus();
			return true;
		}
		return false;
	}

	public void clearHold() {
		reservedBy = null;
		updateStatus();
	}

	public void markDamaged() {
		status = BookStatus.DAMAGED;
	}

	private void updateStatus() {
		if (status == BookStatus.DAMAGED)
			return;

		if (availableCopies > 0) {
			status = BookStatus.AVAILABLE;
		} else {
			status = BookStatus.RESERVED;
		}
	}

	@Override
	public String toString() {
		String hold = (reservedBy == null ? "None" : reservedBy);
		return String.format("[%d] %s by %s | ₹%.2f | Copies: %d | Status: %s | Hold: %s", id, title, author, price,
				availableCopies, status, hold);
	}
}
