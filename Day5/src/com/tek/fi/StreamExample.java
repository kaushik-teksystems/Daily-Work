package com.tek.fi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		stream1();
	}
	
	private static void stream1() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); //Tank
		Stream<Integer> stream = numbers.stream(); //Pipe
		Stream<Integer> squaredStream = stream.map(number -> number * number);
		Stream<Integer> filteredStream = squaredStream.filter((number) -> {
			System.out.println(number);
			return number % 2 != 0;
		});
		System.out.println(filteredStream.count());
//		List<Integer> filteredList = stream.count();
//		System.out.println(filteredList);
	}
	
}
