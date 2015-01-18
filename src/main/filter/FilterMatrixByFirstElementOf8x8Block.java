package main.filter;

import java.util.LinkedList;
import java.util.List;

public class FilterMatrixByFirstElementOf8x8Block {

	public static List<Integer> filter(List<Integer[]> matrix){
		LinkedList<Integer> output = new LinkedList<Integer>();
		for(Integer[] current8x8Block : matrix){
			output.addLast(current8x8Block[0]);
		}
		return output;
	}
	
}
