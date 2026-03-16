package com.tek.list;

import java.util.*;
public class LinkedHashSetex {
	public static void main(String[]args) {
		LinkedHashSet<Integer> linkedHs = new LinkedHashSet<>();
		linkedHs.add(10);
		linkedHs.add(20);
		linkedHs.add(10);
		System.out.println(linkedHs);
	}
}
