package com.tek.DSA;

class StackArray {
	int top = -1;
	int size = 5;
	int[] stack = new int[size];

	void push(int x) {
		if (top == size - 1) {
			System.out.println("Stack overflow");
		} else {
			stack[++top] = x;
		}
	}

	int pop() {
		if (top == -1) {
			System.out.println("Stack Underflow");
			return -1;
		}
		return stack[top--];
	}

	void display() {
		if (top == -1) {
			System.out.println("Stack is Empty");
		} else {
			for (int i = top; i >= 0; i--) {
				System.out.println(stack[i]);
			}
		}
	}

	public static void main(String[] args) {
		StackArray stackArray = new StackArray();
		stackArray.push(42);
		stackArray.push(17);
		stackArray.push(19);
		System.out.println("Before Popping out the elements");
		stackArray.display();
		stackArray.pop();
		System.out.println("After Popping out the element: ");
		stackArray.display();
	}
}
