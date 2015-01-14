package main.calculator;

import java.util.LinkedList;
import java.util.List;

public class CalculatorCategoryByDelta {

	public static List<Integer> calculate(List<Integer> values){
		List<Integer> output = new LinkedList<Integer>();
		for(int i = 0; i < values.size(); i++){
			output.add(UtilityCalculateBitLength.calculate(values.get(i)));
		}
		return output;
	}
	
}
