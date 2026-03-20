package com.tek.bank;

class WithdrawTask implements Runnable {
	private final BankAccount account;
	private final int amount;

	public WithdrawTask(BankAccount account, int amount) {
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		String thread = Thread.currentThread().getName();
		System.out.println(thread + " attempring to withdaw ₹ " + amount);
		boolean success = account.withdraw(amount);
		if (success) {
			System.out.println(thread + " successfully withdrew ₹" + amount);
		} else {
			System.out.println(thread + " Failed to withdraw ₹" + amount + " (Insufficient Balance) ");
		}
	}
}

class DepositTask implements Runnable{
	private final BankAccount account;
	private final int amount;
	
	public DepositTask(BankAccount account, int amount) {
		this.account = account;
		this.amount = amount;
	}

	@Override
	public void run() {
		String thread = Thread.currentThread().getName();
		System.out.println(thread+" depositing ₹"+amount);
		account.deposit(amount);
		System.out.println(thread+" Completed deposit of amount ₹"+amount+ " successfully");
	}
}
