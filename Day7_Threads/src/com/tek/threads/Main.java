package com.tek.threads;

public class Main{
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new MyThread();
		t1.start();
		t1.join();
		System.out.println("Main");
//		thread.sleep(2000);
//		//Using Runnable
//		Thread t = new Thread(new MyTask());
//		t.start();
	}
}
