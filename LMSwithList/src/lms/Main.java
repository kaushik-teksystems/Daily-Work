package lms;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Library lib = new Library();

//        lib.addBook(new Book(1, "Learn Designing", "ABC", 499, 2));
//        lib.addBook(new Book(2, "Learn Java", "XYZ", 799, 1));

        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> handleAddBook(lib);
                case 2 -> handleRemoveBook(lib);
                case 3 -> lib.showAllBooks();
                case 4 -> handleReserveBook(lib);
                case 5 -> handleProcessNextReservation(lib);
                case 6 -> handleShowReservations(lib);
                case 9 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); 
        }
    }

    private static void printMenu() {
        System.out.println("\n===== LIBRARY MANAGEMENT MENU =====");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Show All Books");
        System.out.println("4. Reserve a Book");
        System.out.println("5. Process Next Reservation (allocate first user)");
        System.out.println("6. Show Reservations for a Book");
        System.out.println("7. Borrow a Book (direct, without reservation)");
        System.out.println("8. Return a Book");
        System.out.println("9. Exit");
    }

    private static void handleAddBook(Library lib) {
        int id = readInt("Enter Book ID: ");
        System.out.print("Enter Title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter Author: ");
        String author = sc.nextLine().trim();

        double price = readDouble("Enter Price: ");
        int copies = readInt("Enter Available Copies: ");

        lib.addBook(new Book(id, title, author, price, copies));
    }

    private static void handleRemoveBook(Library lib) {
        int id = readInt("Enter Book ID to remove: ");
        lib.removeBook(id);
    }

    private static void handleReserveBook(Library lib) {
        int id = readInt("Enter Book ID to reserve: ");
        System.out.print("Enter your name: ");
        String user = sc.nextLine().trim();
        lib.reserveBook(id, user);
    }

    private static void handleProcessNextReservation(Library lib) {
        int id = readInt("Enter Book ID to process next reservation: ");
        lib.processNextReservation(id);
    }

    private static void handleShowReservations(Library lib) {
        int id = readInt("Enter Book ID to view reservations: ");
        lib.showReservations(id);
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
