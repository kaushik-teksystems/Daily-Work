package com.tek.list;
import java.util.*;

public class LinkedListEx {
	public static void main(String[]args) {
		List<String> cities = createListOfCities();
		defensiveDownCasting(cities);
		System.out.println(cities.contains("Chennai"));
//		System.out.println(cities);
	}

	private static void defensiveDownCasting(List<String> cities) {
		if(cities instanceof LinkedList) {
			LinkedList<String> linkedlist = ((LinkedList)cities); //Downcasting, done when the function is pointing to linkedlist's type
			linkedlist.addFirst("Chennai");
		}
	}

	private static List<String> createListOfCities() {
		List<String> cities = new ArrayList<>();
		cities.add("Delhi");
		cities.add("Mumbai");
		cities.add("Bangalore");
		return cities;
	}
}

