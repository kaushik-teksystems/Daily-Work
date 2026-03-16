package com.tek.list;

import java.util.*;
public class hashMapEx {
	public static void main(String []args) {
		HashMap<Integer, String> map = new HashMap<>();
		map.put(1,  "java");
		map.put(2, "Pyton");
		map.put(3, "Go");
		map.put(3, "Go Back");
		
		System.out.println(map.size());
		System.out.println(map);
		System.out.println(map.get(3));
	}
}
