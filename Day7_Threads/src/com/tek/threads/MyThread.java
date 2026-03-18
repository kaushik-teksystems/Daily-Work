package com.tek.threads;

class MyThread extends Thread{
	@Override
	public void run() {
//		try {
//			sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("Worker thread is Running.");
	}
}
