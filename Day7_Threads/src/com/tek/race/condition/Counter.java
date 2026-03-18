package com.tek.race.condition;

class Counter {
	int count = 0;
	
	void increment() {
		System.out.println(Thread.currentThread());
		count++;
	}
}
