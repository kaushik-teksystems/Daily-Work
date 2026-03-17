package com.tek.JunitTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MathTest {
	Math math;
	@BeforeEach
	void setup() {
		math = new Math();
	}

	@Test
	void testAdd() {
		int result = math.add(2,  5); //2nd Step: Act
		assertEquals(7, result); //3rd step: Assert
	}
	
	@Test
	void testAddNgativeNumbers() {
		int result = math.add(-2, -5);
		assertEquals(-7, result);
	}
	
	@Test
	void testAddNegativePositiveCode() {
		int result = math.add(-2, 5);
		assertEquals(3, result);
	}

}
