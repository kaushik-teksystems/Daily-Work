package com.tek.DSA;

public class FindDuplicateElementsInArray {
	public static void main(String[] args) {
		int[] array = {2,4,5,2,8,8};
		System.out.println("Duplicate elements in the array are: ");
		findDuplicateElements(array);
	}
	private static void findDuplicateElements(int[] array) {
		for(int i = 0; i< array.length; i++) {
			for(int j = i + 1; j < array.length; j++) {
				if(array[i] == array[j]) {
					System.out.println(array[i]);
					break;
				}
			}
		}
	}
}
