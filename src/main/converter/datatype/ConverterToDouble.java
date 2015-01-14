package main.converter.datatype;

import main.converter.Converter;
import main.exception.common.ExceptionInvalidParameter;

public class ConverterToDouble implements Converter {

	public static double[] convert(int[] values){
		double[] output = new double[values.length];
		for(int i = 0; i < values.length; i++){
			output[i] = (double)values[i];
		}
		return output;
	}
	
	public static double[][] convert(int[][] values){
		if(values.length == 0){
			throw new ExceptionInvalidParameter("konvertierung nur mit einem array groesse möglich die hoeher oder gleich 1 ist");
		}		
		double[][] output = new double[values.length][values[0].length];
		for(int x = 0; x < values.length; x++){
			for(int y = 0; y < values[x].length; y++){
				output[x][y] = (double)values[x][y];
			}
		}
		return output;
	}
	
}
