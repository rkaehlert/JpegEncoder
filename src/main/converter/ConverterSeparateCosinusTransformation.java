package main.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;



public class ConverterSeparateCosinusTransformation implements Converter{

	private static final int BLOCK_SIZE = 8;
	
	public Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix X){
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(X.getRowDimension(), X.getColumnDimension());
		for (int row = 0; row < BLOCK_SIZE; row++) {
             for (int k = 0; k < BLOCK_SIZE; k++) {
            	 double c = (k == 0) ? (1.0 / Math.sqrt(2.0)) : 1.0;
            	 double value = c * Math.sqrt(2.0 / BLOCK_SIZE);
            	 double sum = 0.0;
            	 for(int i = 0; i < BLOCK_SIZE; i++){
	            	 sum += X.getEntry(row, i) * Math.cos(((2.0*i+1) * k * Math.PI) / (2.0 * BLOCK_SIZE));
            	 }
            	 output.setEntry(row, k, sum*value);
             }
        }
		for (int col = 0; col < BLOCK_SIZE; col++) {
            for (int k = 0; k < BLOCK_SIZE; k++) {
	           	 double c = (k == 0) ? (1.0 / Math.sqrt(2.0)) : 1.0;
	           	 double value = c * Math.sqrt(2.0 / BLOCK_SIZE);
	           	 double sum = 0.0;
	           	 for(int i = 0; i < BLOCK_SIZE; i++){
		             sum += output.getEntry(i, col) * Math.cos(((2.0*i+1) * k * Math.PI) / (2.0 * BLOCK_SIZE));
	           	 }
	           	 output.setEntry(k, col, sum*value);
            }
       }
	   return output;
	}
	
	public List<Array2DRowRealMatrix> convert(Array2DRowRealMatrix matrix){
		List<Array2DRowRealMatrix> output = new ArrayList<Array2DRowRealMatrix>();
		for(Array2DRowRealMatrix currentMatrix : ConverterImageTo8x8Block.convert(matrix)){
				Array2DRowRealMatrix matrix8x8 = this.convert8x8(currentMatrix);
				output.add(matrix8x8);
		}
		return output;
	}
	
	
}
