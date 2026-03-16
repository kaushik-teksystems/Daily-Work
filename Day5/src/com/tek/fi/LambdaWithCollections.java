package com.tek.fi;

import java.util.*;
import java.util.function.Consumer;

class MyComparator implements Comparator<String>{
	@Override
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
}
public class LambdaWithCollections {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Java", "Python", "Go");
		comparator();
//		consumer();
	}

	private static void comparator() {
		List<String> names = Arrays.asList("Java", "Python", "Go");
		names.sort((str1, str2) -> {
//			return str2.length() - str1.length(); //Descending Order
			return str1.length() - str2.length(); //Ascending Order
		});
		System.out.println(names);
	}

	private static void consumer() {
		List<String> names = Arrays.asList("Java", "Python", "Go");
		// Consumer<String> consumer = (String name) -> System.out.println(name); ALSO
		// WORKS
//		Consumer<String> consumer = (name) -> System.out.println(name); ALSO WORKS
//		Consumer<String> consumer = name -> System.out.println(name); //ALSO WORKS
		Consumer<String> consumer = name -> {
			System.out.println("name");
			System.out.println(name);
		};
		names.forEach(consumer);
	}
}
