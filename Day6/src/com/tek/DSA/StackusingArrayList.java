package com.tek.DSA;

import java.util.ArrayList;
import java.util.List;

class StackusingArrayList {
	int top = -1;
	int size = 5;
	List<Integer> stack = new ArrayList<>(size);

	void push(int x) {
		if (top == size - 1) {
			System.out.println("Stack Overflow");
		} else {
			top++;
			stack.add(x);
		}
	}
	int pop() {
		if(top == -1) {
			System.out.println("Array is Empty");
			return -1;
		}
		int value = stack.remove(top);
		top--;
		return value;
	}

	void display() {
		if (top == -1) {
			System.out.println("Stack is empty");
		} else {
			for (int i = top; i >= 0; i--) {
				System.out.println(stack.get(i));
			}
		}
	}

	public static void main(String[] args) {
		StackusingArrayList stack = new StackusingArrayList();
		stack.push(12);
		stack.push(15);
		stack.push(17);
		System.out.println("Stack Elements: ");
		stack.display();
		stack.pop();
		System.out.println("Stack Elements after popping: ");
		stack.display();
	}
}
