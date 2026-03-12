package com.tek.list;

import java.util.*;

public class CommonElementsExercise {
	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
		List<Integer> list2 = Arrays.asList(3, 4, 5, 6);
		List<Integer> common = new ArrayList<>();

		for (Integer item1 : list1) {
			for (Integer item2 : list2) {
				if (item1.equals(item2)) {
					if (!common.contains(item1)) {
						common.add(item1);
					}
					break;
				}
			}
		}
		System.out.println("Common elements: " + common);
	}
}