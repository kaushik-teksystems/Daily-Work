package com.tek.list;

import java.util.*;

public class CollectionExample {
	public static void main(String[]args) {
		List<String> fruits = new ArrayList<>();
		
		fruits.add("Apple");
		fruits.add("Mango");
		fruits.add("Banana");
		fruits.add("Mango");
		fruits.add(new String("Mango"));
		
		System.out.println(fruits);
		System.out.println(fruits.get(1)==fruits.get(3));
		System.out.println(fruits.get(1)==fruits.get(4));
	}
}
