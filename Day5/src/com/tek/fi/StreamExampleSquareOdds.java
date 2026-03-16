package com.tek.fi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExampleSquareOdds {
	public static void main(String[] args) {
		stream1();
	}

	private static void stream1() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); // Tank
		Stream<Integer> stream = numbers.stream(); // Pipe
		Stream<Integer> finalStream = stream.filter((number) -> {
			System.out.println(number);
			return number % 2 != 0; //exclude even numbers
		}).map(number -> {
			return number * number;
//			if (number % 2 == 1) {
//				return number * number;
//			} else {
//				return number;
//			}
		});
		List finalList = finalStream.collect(Collectors.toList());
		
		//IMPLEMENTING METHOD REFERENCE:
		
		finalList.forEach(x -> System.out.println(x));
		finalList.forEach(System.out::println);
		
		
//		System.out.println(finalList);
//		Stream<Integer> filteredStream = finalStream.filter((number) -> {
//			System.out.println(number);
//			return number % 2 != 0;
//		});
//		System.out.println(finalStream.count());
//		List<Integer> filteredList = stream.count();
//		System.out.println(filteredList);
	}

}
