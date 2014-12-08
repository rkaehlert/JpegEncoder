package main.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import main.exception.common.ExceptionInvalidParameter;
import main.model.matrix.Matrix2D;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;


public class ConverterDiscreteCosinusTransformation implements Converter {
	
	private static final int BLOCK_SIZE = 8;
	
	private Array2DRowRealMatrix convert8x8(Array2DRowRealMatrix image, final int rows, final int cols){

		if (rows%BLOCK_SIZE!=0 || cols%BLOCK_SIZE!=0) {
	      throw new ExceptionInvalidParameter("zeilen und spalten sollten vielfache von 8 sein");
	    }
		
		
		Array2DRowRealMatrix output = new Array2DRowRealMatrix(rows,cols);
		
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
                 bd = bd.setScale(2, RoundingMode.HALF_DOWN);
                 output.setEntry(u,v,bd.doubleValue());
             }
		 }
		
		return output;
	}
	
	public Array2DRowRealMatrix convert(Array2DRowRealMatrix matrix, final int rows, final int cols){
		Matrix2D output = new Matrix2D(rows,cols);
		for(int i = 0; i < rows; i+=BLOCK_SIZE){
			for(int j = 0; j < cols; j+=BLOCK_SIZE){
				Array2DRowRealMatrix input = (Array2DRowRealMatrix) matrix.getSubMatrix(i, i+7, j, j+7);
				input = this.convert8x8(input, BLOCK_SIZE, BLOCK_SIZE);
				output.setSubMatrix(input.getData(), i, j);
			}
		}
		return output;
	}
	
	
//	public double[][] convert(final double[][] image, final int rows, final int cols){
//		double[][] output = new double[rows][cols];
//		int counter = 0; 
////		for(int i = 0; i < rows; i+=BLOCK_SIZE){
////			for(int j = 0; j < cols; j+=BLOCK_SIZE){
////				double[][] input = new double[BLOCK_SIZE][BLOCK_SIZE];
////				for(int m = 0; m < BLOCK_SIZE; m++){
////					for(int n = 0; n < BLOCK_SIZE; n++){
////						input[m][n] = image[i+m][j+n];					
////					}
////				}
////				input = this.convert8x8(input, rows, cols);
////				output[counter] = input[0];
//////				System.arraycopy(input, 0, output, counter*BLOCK_SIZE, 8);
////				counter++;
////			}
////		}
//		return output;
//	}
	
}
