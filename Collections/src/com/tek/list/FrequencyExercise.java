package com.tek.list;
import java.util.*;

public class FrequencyExercise {
	public static void main(String[]args) {
		List<Integer> list = Arrays.asList(1,2,2,3,2,4);
		int target = 2;
		int frequency = 0;
		for(int num : list) {
			if(num == target) {
				frequency++;
			}
		}
		System.out.println("Frequency of Target in the list: "+ frequency);
	}
}
