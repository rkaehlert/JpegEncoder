package main.comparator;

import java.util.Arrays;


public class ComparatorArrayEquals {

	public static boolean compare(Object[] arr1, Object[] arr2){
		return Arrays.deepEquals(arr1, arr2);
	}
	
	public static boolean compare(Object[][] arr1, Object[][] arr2){
		return Arrays.deepEquals(arr1, arr2);
	}
	
}
