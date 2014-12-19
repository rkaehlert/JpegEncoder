package main.formatter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FormatterRunLengthEncodingByCategory {

	public static List<Integer[]> format(List<Integer[]> dataToFormat){
		List<Integer[]> output = new ArrayList<Integer[]>();
		for(Integer[] currentData : dataToFormat){
			for(int i = 0; i < currentData.length; i+=2 ){
				Integer[] formattedData = new Integer[2];
				formattedData[0] = currentData[i];
				Integer value = formattedData[i+1];
				formattedData[1] = new BigInteger(value.toString()).abs().bitCount();
				output.add(formattedData);
			}
		}
		return output;
	}
	
}
