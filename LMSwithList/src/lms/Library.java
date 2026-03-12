package lms;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added: " + book.getTitle());
    }

    public void removeBook(int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        System.out.println(removed ?
                "Removed book with ID " + id :
                "Book not found.");
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public void reserveBook(int id, String user) {
        Book b = findBook(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        if (b.getReservationList().contains(user)) {
            System.out.println("User already reserved this book.");
            return;
        }

        b.addReservation(user);
        System.out.println("Reserved " + b.getTitle() + " for user: " + user);
    }

    public void processNextReservation(int id) {
        Book b = findBook(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        if (b.getReservationList().isEmpty()) {
            System.out.println("No pending reservations for this book.");
            return;
        }

        String user = b.getReservationList().get(0);

        if (b.bookCopy()) {
            b.getReservationList().remove(0);
            System.out.println("Allocated copy of " + b.getTitle() + " to user: " + user);
        } else {
            System.out.println("No copies available to allocate.");
        }
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }

        System.out.println("\n----- Book List -----");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void showReservations(int id) {
        Book b = findBook(id);
        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("\nReservations for: " + b.getTitle());
        if (b.getReservationList().isEmpty()) {
            System.out.println("No reservations.");
        } else {
            for (String user : b.getReservationList()) {
                System.out.println("- " + user);
            }
        }
    }
}