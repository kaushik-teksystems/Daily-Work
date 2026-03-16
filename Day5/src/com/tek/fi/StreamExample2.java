package com.tek.fi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample2 {
	public static void main(String[] args) {
		stream1();
		streamWithChain();
	}
	
	private static void streamWithChain() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		Stream<Integer> finalStream = numbers.stream().map(number -> number*number).filter((number) ->{
			System.out.println(number);
			return number % 2 != 0;
		});
	}

	private static void stream1() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5); //Tank
		Stream<Integer> stream = numbers.stream(); //Pipe
		Stream<Integer> squaredStream = stream.map(number -> number * number);
		Stream filteredStream = squaredStream.filter((number) -> {
			System.out.println(number);
			return number % 2 != 0;
		});
		System.out.println(filteredStream.count());
//		List<Integer> filteredList = stream.count();
//		System.out.println(filteredList);
	}
	
}
