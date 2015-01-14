package main.formatter;

import java.util.LinkedList;
import java.util.List;

import main.calculator.UtilityCalculateBitLength;

public class FormatterRunLengthEncodingByCategory {

	public static List<List<Integer[]>> format(List<Integer[]> dataToFormat){
		List<List<Integer[]>> output = new LinkedList<List<Integer[]>>();
		for(Integer[] currentData : dataToFormat){
			List<Integer[]> lstFormattedData = new LinkedList<Integer[]>();
			for(int i = 0; i < currentData.length; i+=2 ){
				Integer[] formattedData = new Integer[2];
				formattedData[0] = currentData[i];
				Integer value = currentData[i+1];
				formattedData[1] = UtilityCalculateBitLength.calculate(value);
				lstFormattedData.add(formattedData);
			}
			output.add(lstFormattedData);
		}
		return output;
	}
	
}
