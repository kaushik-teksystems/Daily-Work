import java.util.ArrayList;
import java.util.List;

import LMS.Book;

class Library {

    List<Book> books = new ArrayList<>();

    void addBook(String title, int price) {
        books.add(new Book(title, price));
    }

    void borrowBook(String title) {
        for(Book b : books) {
            if(b.title.equals(title) && b.available) {
                b.available = false;
                System.out.println("Borrowed: " + title);
                return;
            }
        }
        System.out.println("Book not available");
    }

    void displayBooks() {
        for(Book b : books)
            if(b.available)
                System.out.println(b.title);
    }
    
    void find(String keyword) {
    	for(Book b : books) {
    		if(b.title.toLowerCase().contains(keyword.toLowerCase())) {
    			System.out.println(b.title);
    		}
    	}
    }
}