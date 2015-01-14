package main.utility.array;

import java.util.Arrays;

public class UtilityArrayCreateSubArray {

	public static byte[] createSubArray(byte[] arr, int from, int to){
		return Arrays.copyOfRange(arr, from, to);
	}
	
}
