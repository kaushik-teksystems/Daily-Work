package com.tek.DSA;

public class ReverseArray {
	public static void main(String[] args) {
		int[] arr = { 10, 20, 30 };
		System.out.println("Original Array: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		reverseArray(arr);
		System.out.println("Reversed Array: ");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + " ");
		}

	}

	private static void reverseArray(int arr[]) {
		int start = 0;
		int end = arr.length - 1;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;

			start++;
			end--;
		}
	}
}
