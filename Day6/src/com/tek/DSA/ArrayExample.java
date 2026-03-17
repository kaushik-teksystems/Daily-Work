package com.tek.DSA;

import java.util.Scanner;

public class ArrayExample {
	public static void main(String[] args) {
//		int[] numbers = { 10, 20, 30, 40, 20 };
		int[] numbers = new int[10];
//		for(int i = 0; i < numbers.length; i++ ) {
//			numbers[i] = i;
//		}
//		System.out.println(numbers[2]);
		arrayWithCustomObjects();
	}
	private static void arrayWithCustomObjects() {
		String[] books = new String[5];
		Scanner sc = new Scanner(System.in);
		for(int j = 0; j < books.length; j++) {
			System.out.println("Enter book "+ (j+1));
			books[j] = sc.nextLine();
		}
		System.out.println("The books are: ");
		for(int k = 0; k < books.length; k++) {
			System.out.print(books[k]+" ");
		}
	}
	private static void bookArray() {
		Book[] books = new Book[10];
		books[0] = new Book(1, "Learn Java", 149.0F, "Kaushik");
	}
}
