package com.tek.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker2 implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		System.out.println(Thread.currentThread().getName() + " finished.");
	}
}

public class ExecutorServiceDemo {
	public static void main(String[] args) {
		ExecutorService executor1 = Executors.newFixedThreadPool(10);
//		ExecutorService executor2 = Executors.newFixedThreadPool(1);
		
		executor1.submit(new Worker2());
		executor1.shutdown();
		
//		executor2.submit(new Worker2());
//		executor2.shutdown();
	}
}
