package basics;

public class LargestNumber {
	public static void main(String[] args) {
		int result = findLargest(2, 5, 1);
		System.out.println(result);
	}

	static int findLargest(int num1, int num2, int num3) {
		int largest;
		if (num1 >= num2 && num1 >= num3) {
			largest = num1;
		} else if (num2 >= num1 && num2 >= num3) {
			largest = num2;
		} else {
			largest = num3;
		}
		return largest;
	}
}
