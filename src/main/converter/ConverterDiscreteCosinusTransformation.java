package main.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;


public class ConverterDiscreteCosinusTransformation implements Converter {
	
	private static final int BLOCK_SIZE = 8;
	
	private Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix image){
		
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(BLOCK_SIZE,BLOCK_SIZE);
		
		if(image.getColumnDimension() == BLOCK_SIZE && image.getRowDimension() == BLOCK_SIZE)
		 for (int u = 0; u < BLOCK_SIZE; u++) {
             for (int v = 0; v < BLOCK_SIZE; v++) {
             	double Cu = u == 0 ? 1 / Math.sqrt(2.0) : 1;
 	        	double Cv = v == 0 ? 1 / Math.sqrt(2.0) : 1;
 	        	output.setEntry(u,v,(double)(2.0/BLOCK_SIZE) * Cu * Cv);
 	        	double sum = 0.0;
 	        	double partial = (double)2.0*BLOCK_SIZE;
                 for (int x = 0; x < BLOCK_SIZE; x++) {
                         for (int y = 0; y < BLOCK_SIZE; y++) {
                         		double X = ((double)image.getEntry(x,y));
                         		double cos1 = ((double)(2.0*x + 1.0)*(double)u*Math.PI)/partial;
                         		double cos2 = (double)(2.0*y + 1.0)*(double)v*Math.PI/partial;
                         		sum += X*Math.cos(cos1)*Math.cos(cos2);
                         }
                 }
                 
                 BigDecimal bd = new BigDecimal(output.getEntry(u,v) * sum);
               // = bd.setScale(2, RoundingMode.HALF_DOWN);
                 output.setEntry(u,v,bd.doubleValue());
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
