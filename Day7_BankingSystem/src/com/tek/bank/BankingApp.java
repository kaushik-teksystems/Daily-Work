package com.tek.bank;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankingApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter initial balance in ₹: ");
		int initialBalance = sc.nextInt();

		BankAccount account = new BankAccount(initialBalance);

		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		while(true){
			System.out.println("\n ====MULTITHREAD BANKING SYSTEM (ExecutorService)====");
			System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Simulate Parallel Wirthdrawls");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            
            switch(choice) {
            case 1: 
            	System.out.println("Current Balance = ₹"+account.getBalance());
            	break;
  
            case 2: 
            	System.out.println("Enter amount to deposit in ₹:");
            	int depositAmount = sc.nextInt();
            	executor.execute(new DepositTask(account, depositAmount));
            	break;
            	
            case 3: 
            	System.out.println("Enter amount to withdraw in ₹:");
            	int withdrawAmount = sc.nextInt();
            	executor.execute(new WithdrawTask(account, withdrawAmount));
            	break;
            	
            case 4:
            	System.out.println("Simulating two parallel withdrawls of ₹ "+ (initialBalance/2));
            	executor.execute(new WithdrawTask(account, initialBalance/2));
            	executor.execute(new WithdrawTask(account, initialBalance/2));
            	break;
            	
            case 5:
            	System.out.println("Shutting Down the Banking System.");
            	executor.shutdown();
                sc.close();
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice! Try again.");
            }  	
		}
	}
}
