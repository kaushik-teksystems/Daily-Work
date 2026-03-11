package basics;

import java.util.ArrayList;

public class List {
	public static void main(String[]args) {
		manageBooks();
		StringSurprise();
	}
	private static void StringSurprise() {
		String s1 = new String("Book1");
		String s2 = new String("Book2");
		String s3 = new String("Book3");
		System.out.println(s1 == s3);
	}
	private static void manageBooks() {
		ArrayList<String> books = new ArrayList<>();
		books.add("Book1");
		books.add("Book2");
		books.add("Book3");
		System.out.println(books.size());
		books.remove(0);
		System.out.println("After removal: "+books.size());
		books.forEach((book) -> System.out.println(book));
	}
}
