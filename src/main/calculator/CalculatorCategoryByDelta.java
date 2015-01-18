package main.calculator;

import java.util.LinkedList;
import java.util.List;

public class CalculatorCategoryByDelta {

	public static List<Integer> calculate(List<Integer> values){
		LinkedList<Integer> output = new LinkedList<Integer>();
		for(int i = 0; i < values.size(); i++){
			output.addLast(UtilityCalculateBitLength.calculate(values.get(i)));
		}
		return output;
	}
	
}
