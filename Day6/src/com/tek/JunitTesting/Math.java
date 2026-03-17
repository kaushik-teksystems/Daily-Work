package com.tek.JunitTesting;

import java.util.stream.Stream;

class Math {
	int add(int a, int b) {
		return a + b;
	}
	int addWithArray(Integer[] numbers) {
		return Stream.of(numbers)
				.reduce(0, (current, element) -> current+element);
	}
	
	int divide(int dividend, int divisor) {
		return dividend/divisor;
	}
}
