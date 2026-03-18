package com.tek.threads;

class MyTask implements Runnable{
	@Override
	public void run() {
		System.out.println("Task Running.");
	}
}
