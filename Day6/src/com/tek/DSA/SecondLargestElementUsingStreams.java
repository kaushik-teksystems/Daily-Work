package com.tek.DSA;

import java.util.Arrays;
import java.util.Comparator;
public class SecondLargestElementUsingStreams {
	public static void main(String[] args) {
		int[] array = {1,-2, -7, -8};
		System.out.println("Second Largest Element is: "+findSecondLargestElement(array));
	}
	private static int findSecondLargestElement(int[] array) {
		if(array.length <= 2) {
			return Math.min(array[0], array[1]);
		}
		return Arrays.stream(array)
				.distinct()
				.boxed()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst()
				.orElse(Integer.MIN_VALUE);
	}
}
