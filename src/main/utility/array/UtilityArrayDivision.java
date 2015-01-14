package main.utility.array;

import main.exception.common.ExceptionInvalidParameter;

public class UtilityArrayDivision {
	
	public static double[] divide(double[] dividend, double[] divisor){
		if(dividend.length != divisor.length){
			throw new ExceptionInvalidParameter("um zwei zeilen zu konvertieren muessen beide die gleiche groesse haben");
		}
		double[] output = new double[dividend.length];
		for(int i = 0; i < dividend.length; i++){
			output[i] = Math.round(dividend[i] / divisor[i]);
		}
		return output;
	}

}
