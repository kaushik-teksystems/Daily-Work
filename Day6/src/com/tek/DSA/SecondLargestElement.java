package com.tek.DSA;

public class SecondLargestElement {
	public static void main(String[] args) {
		int[] arr = { -100, -88, -90, -70, -10, -2, -3 };
		int secondLargestElement = findSecondLargestElement(arr);
		System.out.println("Second Largest Element in the array is; " + secondLargestElement);
	}

	private static int findSecondLargestElement(int[] arr) {
		int largest = Integer.MIN_VALUE;
		int secondLargest = Integer.MIN_VALUE;
		for (int num : arr) {
			if (num > largest) {
				secondLargest = largest;
				largest = num;
			} else if (num > secondLargest && num != largest) {
				secondLargest = num;
			}
		}
		return secondLargest;
	}
}
