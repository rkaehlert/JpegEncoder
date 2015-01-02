package main.calculator;

import java.util.LinkedList;
import java.util.List;

public class CalculatorDelta {

	public static List<Integer> calculate(List<Integer> values){
		List<Integer> output = new LinkedList<Integer>();
		for(int i = 0; i < values.size(); i++){
			if(i == 0){
				output.add(values.get(i));
			}else{
				output.add(values.get(i) - values.get(i-1));
			}
		}
		return output;
	}
	
}
