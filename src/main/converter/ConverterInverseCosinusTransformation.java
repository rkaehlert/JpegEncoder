package main.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;


public class ConverterInverseCosinusTransformation implements Converter {

	private static final int BLOCK_SIZE = 8;
	
	private Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix image){

//		if (rows%BLOCK_SIZE!=0 || cols%BLOCK_SIZE!=0) {
//	      throw new ExceptionInvalidParameter("zeilen und spalten sollten vielfache von 8 sein");
//	    }
//		if(image.length != rows){
//			throw new ExceptionInvalidParameter("die uebergebene laenge entspricht nicht der groesse des bildes");
//		}
		
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(BLOCK_SIZE,BLOCK_SIZE);
		
		 for (int i = 0; i < BLOCK_SIZE; i++) {
	            for (int j = 0; j < BLOCK_SIZE; j++) {
	        		double sum = 0.0; 
	                for (int x = 0; x < BLOCK_SIZE; x++) {
	                    for (int y = 0; y < BLOCK_SIZE; y++) {
	                    	double Cu = x == 0 ? 1.0 / Math.sqrt(2.0) : 1.0;
	        	        	double Cv = y == 0 ? 1.0 / Math.sqrt(2.0) : 1.0;
	                    	double cos1 = ((2.0*i+1.0) * x * Math.PI) / (2.0*BLOCK_SIZE);
	                    	double cos2 = ((2.0*j+1.0) * y * Math.PI) / (2.0*BLOCK_SIZE);
	                    	sum += (2.0/BLOCK_SIZE) * Cu * Cv * image.getEntry(x,y) * Math.cos(cos1) * Math.cos(cos2);
	                    }
	                }
	                
	                BigDecimal bd = new BigDecimal(sum);
	                bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	                output.setEntry(i,j,bd.doubleValue());
	            }
	        }
		
		return output;
	}
	
	public List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix){
		List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
		for(Array2DRowRealMatrix currentMatrix : ConverterMatrixToBlock.convert(matrix, BLOCK_SIZE)){
				Array2DRowRealMatrix matrix8x8 = this.convert8x8(currentMatrix);
				output.add(matrix8x8);
		}
		return output;
	}	
	
}